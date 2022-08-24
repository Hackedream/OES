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
     * 课程ID
     */
    @TableField("chlid")
    private Integer chlid;

    /**
     * 讲师ID
     */
    @TableField("chuid")
    private Integer chuid;

    /**
     * 审核时间
     */
    @TableField("chTime")
    private LocalDateTime chTime;

    /**
     * 审核状态
     */
    @TableField("chState")
    private Boolean chState;


}
