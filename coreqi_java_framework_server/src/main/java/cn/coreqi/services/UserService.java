package cn.coreqi.services;

import cn.coreqi.entity.TUser;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface UserService {
    IPage<TUser> selectUserPage(IPage page);
}
