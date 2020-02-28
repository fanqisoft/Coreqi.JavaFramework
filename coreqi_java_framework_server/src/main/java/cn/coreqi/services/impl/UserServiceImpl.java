package cn.coreqi.services.impl;

import cn.coreqi.entity.TUser;
import cn.coreqi.entityMapper.TUserMapper;
import cn.coreqi.services.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper userMapper;
    @Override
    public IPage<TUser> selectUserPage(IPage page) {
        return userMapper.selectPage(page,null);
    }
}
