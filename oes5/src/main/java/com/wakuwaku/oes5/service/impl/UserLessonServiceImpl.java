package com.wakuwaku.oes5.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes5.entity.Lesson;
import com.wakuwaku.oes5.entity.User;
import com.wakuwaku.oes5.entity.UserLesson;
import com.wakuwaku.oes5.mapper.UserLessonMapper;
import com.wakuwaku.oes5.service.ILessonService;
import com.wakuwaku.oes5.service.IUserLessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wakuwaku.oes5.service.IUserService;
import io.swagger.models.auth.In;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
@Service
public class UserLessonServiceImpl extends ServiceImpl<UserLessonMapper, UserLesson> implements IUserLessonService {

    @Resource
    UserLessonMapper userLessonMapper;
    @Resource
    IUserService userService;
    @Resource
    ILessonService lessonService;

    @Override
    public List<Lesson> getSameUserLessons(Integer uid) {

        QueryWrapper<UserLesson> userLessonQueryWrapper = new QueryWrapper<>();
        userLessonQueryWrapper.eq("uid", uid);
        List<UserLesson> userLessons = userLessonMapper.selectList(userLessonQueryWrapper);
        if (userLessons != null) {
            int size = userLessons.size();
            Integer[] lidArray = new Integer[size];
            for (int i = 0; i<size; i++) {
                lidArray[i] = userLessons.get(i).getLid();
            }
            List<Integer> lids = Arrays.asList(lidArray);
            List<Lesson> lessons = lessonService.findAllByLids(lids);
            return lessons;
        } else {
            try {
                throw new NotFoundException("用户不存在或未购买任何课程！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }


    }

    @Override
    public List<User> getSameLessonUsers(Integer lid) {

        QueryWrapper<UserLesson> userLessonQueryWrapper = new QueryWrapper<>();
        userLessonQueryWrapper.eq("lid", lid);
        List<UserLesson> userLessons = userLessonMapper.selectList(userLessonQueryWrapper);
        if (userLessons != null) {
            int size = userLessons.size();
            Integer[] uidArray = new Integer[size];
            for (int i = 0; i<size; i++) {
                uidArray[i] = userLessons.get(i).getUid();
            }
            List<Integer> uids = Arrays.asList(uidArray);
            List<User> users = userService.findAllByUids(uids);
            return users;
        } else {
            try {
                throw new NotFoundException("课程不存在或无人购买！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
