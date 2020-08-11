package cn.coreqi.web.modelMapper;

import cn.coreqi.model.TUserModel;
import cn.coreqi.entity.TRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TUserModelMapper {
    TUserModel loadUserByUsername(String username);
    List<TRole> getUserRolesById(Integer id);
}
