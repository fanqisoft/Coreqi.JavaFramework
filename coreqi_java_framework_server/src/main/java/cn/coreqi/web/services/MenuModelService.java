package cn.coreqi.web.services;

import cn.coreqi.web.model.TMenuModel;

import java.util.List;

public interface MenuModelService {
    List<TMenuModel> getAllMenusWithRole();
    List<TMenuModel> getMenusByUserId(Integer userId);
}
