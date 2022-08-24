package com.wakuwaku.oes3.service;

import com.wakuwaku.oes3.entity.Lesson;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
public interface ILessonService extends IService<Lesson> {

    Lesson findByName(String info);

    List<Lesson> findAllTeacherLessons(Integer uid);

    List<Lesson> findAllLessons();
}
