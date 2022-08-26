package com.wakuwaku.oes6.controller;



import com.wakuwaku.oes6.entity.Lesson;
import com.wakuwaku.oes6.entity.User;
import com.wakuwaku.oes6.service.ILessonService;
import com.wakuwaku.oes6.service.IUserLessonService;
import com.wakuwaku.oes6.utils.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Resource
    IUserLessonService userLessonService;
    @Resource
    ILessonService lessonService;

    /**
     * 查询所有购买此课程的用户
     * @param lid
     * @return
     */
    @GetMapping("/getUsers")
    @ResponseBody
    public R getUsers(Integer lid) {

        List<User> users = userLessonService.getSameLessonUsers(lid);
        if (users != null) {
            return R.ok().message("购买此课程的用户如下：").data("Users", users);
        } else {
            return R.error().message("此课程尚无用户购买！");
        }

    }

    /**
     * 查询所有已过审的课程
     * @return
     */
    @GetMapping("/getPassLessons")
    @ResponseBody
    public R getPassLessons() {

        List<Lesson> lessons = lessonService.findAllPass();
        if (lessons != null) {
            return R.ok().message("课程列表如下：").data("Lessons", lessons);
        } else {
            return R.error().message("暂无课程！");
        }

    }

}
