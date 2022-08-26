package com.wakuwaku.oes5.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
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
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @TableId(value = "lid", type = IdType.AUTO)
    private Integer lid;

    /**
     * 课程名称
     */
    @TableField("lName")
    private String lName;

    /**
     * 课程类别
     * 0为语文
     * 1为数学
     * 2为英语
     * 3为物理
     * 4为化学
     * 5为生物
     * 6为政治
     * 7为历史
     * 8为地理
     * 9为计算机
     */
    @TableField("lCategory")
    private Integer lCategory;

    /**
     * 课程简介
     */
    @TableField("lIntro")
    private String lIntro;

    /**
     * 课程详情
     */
    @TableField("lInfo")
    private String lInfo;

    /**
     * 课程价格
     */
    @TableField("lPrice")
    private Double lPrice;

    /**
     * 课程评分
     */
    @TableField("lRank")
    private Double lRank;

    /**
     * 课程时长
     */
    @TableField("lTime")
    private Double lTime;

    /**
     * 课程折扣
     */
    @TableField("lDiscount")
    private Double lDiscount;

    /**
     * 课程点赞数
     */
    @TableField("lLikeNum")
    private Integer lLikeNum;

    /**
     * 课程收藏数
     */
    @TableField("lCollectNum")
    private Integer lCollectNum;

    /**
     * 课程上传者ID
     */
    @TableField("luid")
    private Integer luid;

    /**
     * 课程上传者
     */
    @TableField("luName")
    private String luName;

    /**
     * 课程上传时间
     */
    @TableField(value = "luTime", fill = FieldFill.INSERT)
    private Date luTime;

    /**
     * 课程审核状态
     * 0为未审核
     * 1为已通过
     * 2为未通过
     */
    @TableField("lState")
    private Integer lState;


}
