package com.wakuwaku.oes5.entity;

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
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    @TableId(value = "qid", type = IdType.AUTO)
    private Integer qid;

    /**
     * 提问者ID
     */
    @TableField("quid")
    private Integer quid;

    /**
     * 提问者昵称
     */
    @TableField("qUsername")
    private String qUsername;

    /**
     * 提问内容
     */
    @TableField("qContent")
    private String qContent;

    /**
     * 提问时间
     */
    @TableField("qTime")
    private LocalDateTime qTime;

    /**
     * 提问点赞数
     */
    @TableField("qLikeNum")
    private Integer qLikeNum;

    /**
     * 提问收藏数
     */
    @TableField("qCollectNum")
    private Integer qCollectNum;

    /**
     * 课程提问ID
     */
    @TableField("qlid")
    private Integer qlid;

    /**
     * 课程名称
     */
    @TableField("qlName")
    private String qlName;


}
