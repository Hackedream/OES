package com.wakuwaku.oes6.service;

import com.wakuwaku.oes6.entity.Lesson;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
public interface ILessonService extends IService<Lesson> {

    boolean infoIsCorrect(Lesson lesson);

    Lesson findByName(String info);

    List<Lesson> findAllTeacherLessons(Integer uid);

    List<Lesson> findAllLessons();

    List<Lesson> findAllLessonsOfState(Integer state);

    Lesson findByInfo(String info);

    List<Lesson> findAllByLids(List<Integer> lids);

    List<Lesson> findAllPass();

    List<Lesson> findLessonsByKey(String keyword);

    Lesson uploadLessonPhoto(Integer lid, String url);

    Lesson uploadLessonAudio(Integer lid, String url);

    Lesson uploadLessonVideo(Integer lid, String url);

    List<Lesson> findLessonsByCategory(Integer category);

    List<Lesson> findLessonsByPriceSection(Double minPrice, Double maxPrice);
}
