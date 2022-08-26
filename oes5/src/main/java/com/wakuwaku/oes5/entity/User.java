package com.wakuwaku.oes5.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户昵称
     */
    @TableField("username")
    private String username;

    /**
     * 用户姓名
     */
    @TableField("uName")
    private String uName;

    /**
     * 注册时间
     */
    @TableField(value = "uRegiTime", fill = FieldFill.INSERT)
    private Date uRegiTime;

    /**
     * 更新时间
     */
    @TableField(value = "uUpdateTime", fill = FieldFill.INSERT_UPDATE)
    private Date uUpdateTime;

    /**
     * 用户性别
     * 0为女性
     * 1为男性
     */
    @TableField("uGender")
    private Boolean uGender;

    /**
     * 用户生日
     */
    @TableField("uBirth")
    private Date uBirth;

    /**
     * 用户手机号
     */
    @TableField("uTel")
    private String uTel;

    /**
     * 用户邮箱
     */
    @TableField("uEmail")
    private String uEmail;

    /**
     * 用户密码
     */
    @TableField("uPassword")
    private String uPassword;

    /**
     * 用户余额
     */
    @TableField("uBalance")
    private Double uBalance;

    /**
     * 观看时长
     */
    @TableField("uWatching")
    private Double uWatching;

    /**
     * 用户类型
     * 0为学生
     * 1为讲师
     */
    @TableField("uType")
    private Boolean uType;

    /**
     * 讲师评分
     * 该属性仅供讲师使用
     */
    @TableField("uRank")
    private Double uRank;

    /**
     * 课程数量
     * 该属性仅供讲师使用
     */
    @TableField("uLessonNum")
    private Integer uLessonNum;

    /**
     * 支付密码
     */
    @TableField("uPayPassword")
    private Integer uPayPassword;


}
