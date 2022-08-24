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
public class Searchlist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索人ID
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    /**
     * 关键词
     */
    @TableField("sKeyword")
    private String sKeyword;

    /**
     * 搜索时间
     */
    @TableField("sTime")
    private LocalDateTime sTime;


}
