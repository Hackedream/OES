package com.wakuwaku.oes5.controller;


import com.wakuwaku.oes5.entity.Lesson;
import com.wakuwaku.oes5.entity.User;
import com.wakuwaku.oes5.service.ILessonService;
import com.wakuwaku.oes5.service.IUserLessonService;
import com.wakuwaku.oes5.utils.result.R;
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
 * @since 2022-08-25
 */
@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Resource
    ILessonService lessonService;
    @Resource
    IUserLessonService userLessonService;

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

}
