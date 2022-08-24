package com.wakuwaku.oes3.entity;

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
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    /**
     * 管理员账号
     */
    @TableField("aAccount")
    private String aAccount;

    /**
     * 账号密码
     */
    @TableField("aPassword")
    private String aPassword;

    /**
     * 管理员姓名
     */
    @TableField("aName")
    private String aName;

    /**
     * 管理员手机号
     */
    @TableField("aTel")
    private String aTel;

    /**
     * 管理员邮箱
     */
    @TableField("aEmail")
    private String aEmail;


}
