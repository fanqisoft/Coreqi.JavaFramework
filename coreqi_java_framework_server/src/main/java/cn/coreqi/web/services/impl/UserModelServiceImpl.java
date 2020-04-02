package cn.coreqi.web.services.impl;

import cn.coreqi.core.TUserModel;
import cn.coreqi.entityMapper.TUserMapper;
import cn.coreqi.web.modelMapper.TUserModelMapper;
import cn.coreqi.web.services.UserModelService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserModelServiceImpl implements UserModelService {
    @Autowired
    private TUserModelMapper userModelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUserModel userModel = userModelMapper.loadUserByUsername(username);
        if(userModel == null){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        userModel.setRoles(userModelMapper.getUserRolesById(userModel.getId()));
        return userModel;
    }
}
