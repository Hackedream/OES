package com.wakuwaku.oes5.service;

import com.wakuwaku.oes5.entity.Lesson;
import com.wakuwaku.oes5.entity.User;
import com.wakuwaku.oes5.entity.UserLesson;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
public interface IUserLessonService extends IService<UserLesson> {

    //某用户购买的课程
    List<Lesson> getSameUserLessons(Integer uid);

    //购买某课程的所有用户
    List<User> getSameLessonUsers(Integer lid);

}
