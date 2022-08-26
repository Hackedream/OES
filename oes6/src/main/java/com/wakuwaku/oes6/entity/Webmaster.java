package com.wakuwaku.oes6.entity;

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
 * @author Ania
 * @since 2022-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Webmaster implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @TableId(value = "wid", type = IdType.AUTO)
    private Integer wid;

    /**
     * 管理员账号
     */
    @TableField("wAccount")
    private String wAccount;

    /**
     * 账号密码
     */
    @TableField("wPassword")
    private String wPassword;

    /**
     * 管理员姓名
     */
    @TableField("wName")
    private String wName;

    /**
     * 管理员手机号
     */
    @TableField("wTel")
    private String wTel;

    /**
     * 管理员邮箱
     */
    @TableField("wEmail")
    private String wEmail;


}
