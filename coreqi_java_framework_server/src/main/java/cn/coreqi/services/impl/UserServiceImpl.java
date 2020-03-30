package cn.coreqi.services.impl;

import cn.coreqi.core.TUser;
import cn.coreqi.entityMapper.TUserMapper;
import cn.coreqi.services.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper userMapper;
    @Override
    public IPage<TUser> selectUserPage(IPage page) {
        return userMapper.selectPage(page,null);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userMapper.loadUserByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
