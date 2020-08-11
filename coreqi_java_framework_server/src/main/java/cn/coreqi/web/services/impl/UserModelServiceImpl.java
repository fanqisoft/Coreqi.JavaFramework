package cn.coreqi.web.services.impl;

import cn.coreqi.model.TUserModel;
import cn.coreqi.web.modelMapper.TUserModelMapper;
import cn.coreqi.web.services.UserModelService;
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
