package com.wakuwaku.oes5.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Checklist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核ID
     */
    @TableId(value = "chid", type = IdType.AUTO)
    private Integer chid;

    /**
     * 管理员ID
     */
    @TableField("chaid")
    private Integer chaid;

    /**
     * 管理员姓名
     */
    @TableField("chaName")
    private String chaName;

    /**
     * 课程ID
     */
    @TableField("chlid")
    private Integer chlid;

    /**
     * 课程名称
     */
    @TableField("chlName")
    private String chlName;

    /**
     * 讲师ID
     */
    @TableField("chuid")
    private Integer chuid;

    /**
     * 讲师姓名
     */
    @TableField("chuName")
    private String chuName;

    /**
     * 审核时间
     */
    @TableField(value = "chTime", fill = FieldFill.INSERT)
    private LocalDateTime chTime;

    /**
     * 审核状态（0为未通过，1为已通过）
     */
    @TableField("chState")
    private Boolean chState;


}
