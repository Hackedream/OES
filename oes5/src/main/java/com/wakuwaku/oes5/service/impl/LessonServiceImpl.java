package com.wakuwaku.oes5.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes5.entity.Lesson;
import com.wakuwaku.oes5.entity.UserLesson;
import com.wakuwaku.oes5.mapper.LessonMapper;
import com.wakuwaku.oes5.service.ILessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wakuwaku.oes5.utils.result.R;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements ILessonService {

    @Resource
    LessonMapper lessonMapper;

    @Override
    public boolean infoIsCorrect(Lesson lesson) {

        //课程名称重复
        QueryWrapper<Lesson> nameWrapper = new QueryWrapper<>();
        nameWrapper.eq("lName", lesson.getLName());
        if (lessonMapper.selectOne(nameWrapper) != null) {
            try {
                throw new Exception("课程名称已被使用！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }

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

    @Override
    public List<Lesson> findAllLessonsOfState(Integer state) {

        QueryWrapper<Lesson> lessonQueryWrapper = new QueryWrapper<>();
        lessonQueryWrapper.eq("lState", state);
        List<Lesson> lessons = lessonMapper.selectList(lessonQueryWrapper);
        if (lessons == null) {
            try {
                throw new NotFoundException("该状态下暂无任何课程！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return lessons;

    }

    @Override
    public Lesson findByInfo(String info) {

        //通过课程名查询
        Lesson lesson = this.findByName(info);
        if (lesson != null) {
            return lesson;
        }

        //通过id查询
        lesson = this.getById(info);
        return lesson;

    }

    @Override
    public List<Lesson> findAllByLids(List<Integer> lids) {

        List<Lesson> lessons = lessonMapper.selectBatchIds(lids);
        if (lessons == null) {
            try {
                throw new NotFoundException("未查询到课程！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return lessons;

    }

}
