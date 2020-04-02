package cn.coreqi.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 用于处理payload请求参数的用户名密码鉴权过滤器
 */
public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String COREQI_FORM_USERNAME_KEY = "userName";
    public static final String COREQI_FORM_PASSWORD_KEY = "password";

    private String usernameParameter = COREQI_FORM_USERNAME_KEY;    //请求中携带用户名的参数名称
    private String passwordParameter = COREQI_FORM_PASSWORD_KEY;    //请求中携带密码的参数名称

    private boolean postOnly = true;    //指定当前过滤器是否只处理POST请求

    @Autowired
    private ObjectMapper objectMapper;

    // ~ Constructors
    // ===================================================================================================

    public CustomUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/account", "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Nullable
    protected String obtainPassword(HttpServletRequest request) throws IOException {
        Map<String,String> data = objectMapper.readValue(IOUtils.toString(request.getReader()),Map.class);
        return (String) data.get(passwordParameter);
    }

    @Nullable
    protected String obtainUsername(HttpServletRequest request) throws IOException {
        Map<String,String> data = objectMapper.readValue(IOUtils.toString(request.getReader()),Map.class);
        return (String) data.get(usernameParameter);
    }


    /**
     * 把请求的详情，例如请求ip、SessionId等设置到验证请求中去
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return usernameParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }
}
