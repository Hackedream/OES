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
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回答ID
     */
    @TableId(value = "anid", type = IdType.AUTO)
    private Integer anid;

    /**
     * 问题ID
     */
    @TableField("anqid")
    private Integer anqid;

    /**
     * 回答者ID
     */
    @TableField("anuid")
    private Integer anuid;

    /**
     * 回答者昵称
     */
    @TableField("anUsername")
    private String anUsername;

    /**
     * 提问者ID
     */
    @TableField("anuuid")
    private Integer anuuid;

    /**
     * 提问者昵称
     */
    @TableField("anuUsername")
    private String anuUsername;

    /**
     * 回答内容
     */
    @TableField("anInfo")
    private String anInfo;

    /**
     * 回答时间
     */
    @TableField("anTime")
    private LocalDateTime anTime;

    /**
     * 回答点赞数
     */
    @TableField("anLikeNum")
    private Integer anLikeNum;


}
