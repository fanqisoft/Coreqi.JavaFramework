package cn.coreqi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fanqi
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu")
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("url")
    private String url;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    @TableField("name")
    private String name;

    @TableField("iconCls")
    private String iconCls;

    @TableField("keepAlive")
    private Boolean keepAlive;

    @TableField("requireAuth")
    private Boolean requireAuth;

    @TableField("parentId")
    private Integer parentId;

    @TableField("enabled")
    private Boolean enabled;


}
