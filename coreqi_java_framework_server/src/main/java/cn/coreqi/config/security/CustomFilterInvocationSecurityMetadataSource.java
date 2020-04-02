package cn.coreqi.config.security;

import cn.coreqi.entity.TMenu;
import cn.coreqi.entity.TRole;
import cn.coreqi.web.model.TMenuModel;
import cn.coreqi.web.modelMapper.TMenuModelMapper;
import cn.coreqi.web.services.MenuModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 自定义权限拦截
 * 通过当前的请求地址，获取该地址需要的用户角色
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuModelService menuModelService;

    //对类似于URL的字符串做匹配的工具类
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 返回本次访问需要的权限，可以有多个权限
     * 如果返回null的话，意味着当前这个请求不需要任何角色就能访问，甚至不需要登录
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) o;
        String requestUrl = fi.getRequestUrl();
        List<TMenuModel> menus = menuModelService.getAllMenusWithRole();
        for (TMenuModel menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {  //如果请求的url和菜单的url一致
                List<TRole> roles = menu.getRoles();    //拿到该菜单所需的权限列表
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);  //将权限列表返回
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN"); //未匹配到的路径则返回ROLE_LOGIN
    }

    /**
     * 如果返回了所有定义的权限资源，Spring Security会在启动时校验每个ConfigAttribute是否配置正确，不需要校验直接返回null
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 返回类对象是否支持校验，web项目一般使用FilterInvocation来判断，或者直接返回true
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        //return true;
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
