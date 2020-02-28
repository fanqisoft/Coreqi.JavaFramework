package cn.coreqi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("passWord")
    private String passWord;

    /**
     * 用户姓名
     */
    @TableField("realName")
    private String realName;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 住址
     */
    @TableField("address")
    private String address;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 登录时间
     */
    @TableField("loginTime")
    private LocalDateTime loginTime;

    /**
     * 上次登录时间
     */
    @TableField("lastLoginTime")
    private LocalDateTime lastLoginTime;

    /**
     * 登录次数
     */
    @TableField("loginCount")
    private Integer loginCount;

    /**
     * 用户状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否删除
     */
    @TableField("isDel")
    private Integer isDel;


}
