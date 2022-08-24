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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 评论内容
     */
    @TableField("cInfo")
    private String cInfo;

    /**
     * 评论时间
     */
    @TableField("cTime")
    private LocalDateTime cTime;

    /**
     * 评论楼层
     */
    @TableField("cFloor")
    private Integer cFloor;

    /**
     * 评论人ID
     */
    @TableField("cuid")
    private Integer cuid;

    /**
     * 评论人昵称
     */
    @TableField("cUsername")
    private String cUsername;

    /**
     * 评论点赞数
     */
    @TableField("cLikeNum")
    private Integer cLikeNum;

    /**
     * 评论类型
     * true = 评论
     * false = 回复
     */
    @TableField("cType")
    private Boolean cType;

    /**
     * 评论回复人ID
     */
    @TableField("cuuid")
    private Integer cuuid;

    /**
     * 评论回复人昵称
     */
    @TableField("cuUsername")
    private String cuUsername;


}
