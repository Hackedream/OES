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
public class Indent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "inid", type = IdType.AUTO)
    private Integer inid;

    /**
     * 卖家ID
     */
    @TableField("inStuID")
    private Integer inStuID;

    /**
     * 卖家姓名
     */
    @TableField("inStuUsername")
    private String inStuUsername;

    /**
     * 买家ID
     */
    @TableField("inTeaID")
    private Integer inTeaID;

    /**
     * 买家姓名
     */
    @TableField("inTeaUsername")
    private String inTeaUsername;

    /**
     * 课程ID
     */
    @TableField("inlid")
    private Integer inlid;

    /**
     * 课程名称
     */
    @TableField("inlName")
    private String inlName;

    /**
     * 订单创建时间
     */
    @TableField(value = "inCreateTime", fill = FieldFill.INSERT)
    private Date inCreateTime;

    /**
     * 订单状态（0为取消支付，1为已支付）
     */
    @TableField("inState")
    private Boolean inState;

    /**
     * 订单完成时间
     */
    @TableField(value = "inCompleteTime", fill = FieldFill.INSERT_UPDATE)
    private Date inCompleteTime;


}
