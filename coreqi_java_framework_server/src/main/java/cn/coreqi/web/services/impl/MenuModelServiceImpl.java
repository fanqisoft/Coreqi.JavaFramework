package cn.coreqi.web.services.impl;

import cn.coreqi.web.model.TMenuModel;
import cn.coreqi.web.modelMapper.TMenuModelMapper;
import cn.coreqi.web.services.MenuModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuModelServiceImpl implements MenuModelService {

    @Autowired
    private TMenuModelMapper menuModelMapper;

    @Override
    public List<TMenuModel> getAllMenusWithRole() {
        return menuModelMapper.getAllMenusWithRole();
    }
}
