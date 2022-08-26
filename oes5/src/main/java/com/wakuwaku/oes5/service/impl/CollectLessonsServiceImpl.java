package com.wakuwaku.oes5.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes5.entity.CollectLessons;
import com.wakuwaku.oes5.entity.Lesson;
import com.wakuwaku.oes5.entity.UserLesson;
import com.wakuwaku.oes5.mapper.CollectLessonsMapper;
import com.wakuwaku.oes5.service.ICollectLessonsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wakuwaku.oes5.service.ILessonService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
@Service
public class CollectLessonsServiceImpl extends ServiceImpl<CollectLessonsMapper, CollectLessons> implements ICollectLessonsService {

    @Resource
    CollectLessonsMapper collectLessonsMapper;
    @Resource
    ILessonService lessonService;

    @Override
    public List<Lesson> getSameUserLessons(Integer uid) {

        QueryWrapper<CollectLessons> collectLessonsQueryWrapper = new QueryWrapper<>();
        collectLessonsQueryWrapper.eq("uid", uid);
        List<CollectLessons> userLessons = collectLessonsMapper.selectList(collectLessonsQueryWrapper);
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
                throw new NotFoundException("用户不存在或未收藏任何课程！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}
