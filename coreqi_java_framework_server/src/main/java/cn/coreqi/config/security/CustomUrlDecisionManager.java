package cn.coreqi.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 为当前的访问规则进行决策，是否给予访问的权限
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    /**
     * 该方法决定该权限是否有权限访问该资源，如果没登陆就为游客，登陆了就是该用户对应的权限
     * @param authentication    当前登录用户信息及用户权限信息
     * @param object 就是FilterInvocation对象，可以得到request等web资源
     * @param configAttributes  访问资源所需要的权限（可能有多个）
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请登录!");
                }else {
                    return;
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员!");
    }

    /**
     * 用于确定AccessDecisionManager是否可以处理传递的ConfigAttribute
     * @param attribute
     * @return
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * 由安全拦截器 implementation 调用，以确保配置的AccessDecisionManager支持安全拦截器将呈现的安全 object 类型
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
