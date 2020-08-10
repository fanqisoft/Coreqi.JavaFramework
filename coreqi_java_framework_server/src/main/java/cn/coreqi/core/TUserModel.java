package cn.coreqi.core;

import cn.coreqi.entity.TRole;
import cn.coreqi.entity.TUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "封装用户Model类")
public class TUserModel extends TUser implements UserDetails {

    /**
     * 角色列表
     */
    @ApiModelProperty(value = "角色列表",required = false)
    private List<TRole> roles;

    /**
     * 描述当前用户的角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(TRole role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * 账号是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活，启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return getEnabled();
    }
}
