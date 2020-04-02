package cn.coreqi.config.security;

import cn.coreqi.core.RespBean;
import cn.coreqi.test.BodyReaderHttpServletRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class VerificationCodeFilter extends GenericFilter {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(req);

        if ("POST".equals(req.getMethod()) && "/login/account".equals(req.getServletPath())) {
            //登录请求
            String code = req.getParameter("code");
            if(StringUtils.isEmpty(code)){
                Map<String,String> data = objectMapper.readValue(IOUtils.toString(requestWrapper.getReader()),Map.class);
                if(data.isEmpty() || !data.keySet().contains("code")){
                    filterChain.doFilter(requestWrapper, resp);
                }
                code = data.get("code");
            }
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            if (code == null || verify_code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())) {
                //验证码不正确
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(RespBean.error("验证码填写错误")));
                out.flush();
                out.close();
                return;
            } else {
                filterChain.doFilter(requestWrapper, resp);
            }
        } else {
            filterChain.doFilter(requestWrapper, resp);
        }
    }
}
