package com.wakuwaku.oes3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes3.entity.Lesson;
import com.wakuwaku.oes3.entity.User;
import com.wakuwaku.oes3.mapper.LessonMapper;
import com.wakuwaku.oes3.service.ILessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements ILessonService {

    @Resource
    LessonMapper lessonMapper;

    @Override
    public Lesson findByName(String info) {
        QueryWrapper<Lesson> lessonQueryWrapper = new QueryWrapper<>();
        lessonQueryWrapper.eq("lName", info);
        Lesson lesson = lessonMapper.selectOne(lessonQueryWrapper);
        if(lesson == null) {
            try {
                throw new NotFoundException("该课程不存在！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return lesson;
    }

    @Override
    public List<Lesson> findAllTeacherLessons(Integer uid) {
        QueryWrapper<Lesson> lessonQueryWrapper = new QueryWrapper<>();
        lessonQueryWrapper.eq("luid", uid);
        List<Lesson> lessons = lessonMapper.selectList(lessonQueryWrapper);
        if (lessons == null) {
            try {
                throw new NotFoundException("未搜索到该讲师所上传的课程！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return lessons;
    }

    @Override
    public List<Lesson> findAllLessons() {

        QueryWrapper wrapper = new QueryWrapper();
        List<Lesson> lessons = this.baseMapper.selectList(wrapper);
        return lessons;

    }
}
