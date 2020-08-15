package cn.coreqi.beanConverter;

import cn.coreqi.entity.TUser;
import cn.coreqi.vo.UserVo;
import com.alibaba.druid.support.json.JSONUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    //更多官方示例（https://github.com/mapstruct/mapstruct-examples）。
    //@Mapping(source = "username", target = "username"/* constant = "fanqi" */ )
    //@Mapping(target = "address", expression = "java(addressToString(userVo.getAddress()))")
    //@Mapping(target = "birthday",dateFormat = "yyyy-MM-dd HH:mm:ss")
    TUser voToDto(UserVo userVo);

    /**
     * 定义一个默认方法，用于将 HomeAddress 类型转换为 String类型
     * @param address
     * @return
     */
//    default String addressToString(HomeAddress address){
//        return JSONUtils.toJSONString(address);
//    }
}
