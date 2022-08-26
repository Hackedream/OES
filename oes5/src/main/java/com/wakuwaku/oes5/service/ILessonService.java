package com.wakuwaku.oes5.service;

import com.wakuwaku.oes5.entity.Lesson;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wakuwaku.oes5.entity.User;
import com.wakuwaku.oes5.entity.UserLesson;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
public interface ILessonService extends IService<Lesson> {

    boolean infoIsCorrect(Lesson lesson);

    Lesson findByName(String info);

    List<Lesson> findAllTeacherLessons(Integer uid);

    List<Lesson> findAllLessons();

    List<Lesson> findAllLessonsOfState(Integer state);

    Lesson findByInfo(String info);

    List<Lesson> findAllByLids(List<Integer> lids);

}
