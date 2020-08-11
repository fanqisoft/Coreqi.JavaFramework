package cn.coreqi.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 住宅电话
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 联系地址
     */
    @TableField("address")
    private String address;

    @TableField("enabled")
    private Boolean enabled;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用户界面
     */
    @TableField("userface")
    private String userface;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}
