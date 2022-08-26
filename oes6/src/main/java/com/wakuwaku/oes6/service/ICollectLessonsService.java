package com.wakuwaku.oes6.service;

import com.wakuwaku.oes6.entity.CollectLessons;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wakuwaku.oes6.entity.Lesson;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
public interface ICollectLessonsService extends IService<CollectLessons> {

    List<Lesson> getSameUserLessons(Integer uid);

}
