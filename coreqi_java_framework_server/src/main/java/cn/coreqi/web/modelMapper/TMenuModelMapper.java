package cn.coreqi.web.modelMapper;

import cn.coreqi.web.model.TMenuModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TMenuModelMapper {
    List<TMenuModel> getAllMenusWithRole();
}
