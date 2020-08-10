package cn.coreqi.dao;

import cn.coreqi.entity.TUser;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TUserDao {
    @Select("select * from T_user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "userName")
    })
    List<TUser> selectAllUser();

    @DeleteProvider(type = cn.coreqi.dao.impl.TUserDaoImpl.class, method = "deleteUsers")
    int deleteUsers(List<TUser> users);
}
