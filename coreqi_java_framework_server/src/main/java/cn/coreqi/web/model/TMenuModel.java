package cn.coreqi.web.model;

import cn.coreqi.entity.TMenu;
import cn.coreqi.entity.TRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMenuModel extends TMenu {
    private List<TMenuModel> children;
    private List<TRole> roles;
}
