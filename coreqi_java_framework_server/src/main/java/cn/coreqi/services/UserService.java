package cn.coreqi.services;

import cn.coreqi.core.TUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    IPage<TUser> selectUserPage(IPage page);
}
