package com.wakuwaku.oes3.entity;

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
 * @author Ania
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
     * 卖家ID
     */
    @TableField("oStuID")
    private Integer oStuID;

    /**
     * 卖家姓名
     */
    @TableField("oStuUsername")
    private String oStuUsername;

    /**
     * 买家ID
     */
    @TableField("oTeaID")
    private Integer oTeaID;

    /**
     * 买家姓名
     */
    @TableField("oTeaUsername")
    private String oTeaUsername;

    /**
     * 课程ID
     */
    @TableField("olid")
    private Integer olid;

    /**
     * 课程名称
     */
    @TableField("olName")
    private String olName;

    /**
     * 订单创建时间
     */
    @TableField("oCreateTime")
    private LocalDateTime oCreateTime;

    /**
     * 订单状态
     */
    @TableField("oState")
    private String oState;

    /**
     * 订单完成时间
     */
    @TableField("oCompleteTime")
    private LocalDateTime oCompleteTime;


}
