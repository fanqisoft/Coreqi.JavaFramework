package cn.coreqi.dao.impl;

import cn.coreqi.entity.TUser;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class TUserDaoImpl {
    public String deleteUsers(List<TUser> users){
        return new SQL(){{
            DELETE_FROM("T_user");
            for (TUser u : users){
                OR();
                WHERE(" id=" + u.getId());
            }
        }}.toString();
    }
}
