package com.wakuwaku.oes5.controller;


import com.wakuwaku.oes5.entity.Indent;
import com.wakuwaku.oes5.entity.Lesson;
import com.wakuwaku.oes5.entity.User;
import com.wakuwaku.oes5.entity.Webmaster;
import com.wakuwaku.oes5.service.IIndentService;
import com.wakuwaku.oes5.service.ILessonService;
import com.wakuwaku.oes5.service.IUserService;
import com.wakuwaku.oes5.service.IWebmasterService;
import com.wakuwaku.oes5.utils.result.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
@RestController
@RequestMapping("/webmaster")
public class WebmasterController {

    @Resource
    IWebmasterService webmasterService;
    @Resource
    IUserService userService;
    @Resource
    IIndentService indentService;
    @Resource
    ILessonService lessonService;

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(String account, String password) {

        Webmaster webmaster = webmasterService.login(account, password);
        if (webmaster != null) {
            return R.ok().message("登录成功！\n").data("Webmaster", webmaster);
        } else {
            return R.error().message("该管理员不存在或密码错误，请重试！");
        }

    }

    /**
     * 用户添加
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public R addUser(@RequestBody User user) {

        boolean flag = userService.save(user);
        if (flag) {
            return R.ok().message("用户添加成功！\n").data("User", user);
        } else {
            return R.error().message("用户添加失败，请重试！");
        }

    }

    /**
     * 用户删除
     * @param uid
     * @return
     */
    @GetMapping("/delUser")
    @ResponseBody
    public R delUser(Integer uid) {

        boolean flag = userService.removeById(uid);
        if (flag) {
            return R.ok().message("用户删除成功！");
        } else {
            return R.error().message("用户删除失败，请重试！");
        }

    }

    /**
     * 用户信息修改
     * @param user
     * @return
     */
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public R updateUserInfo(@RequestBody User user) {

        boolean flag = userService.saveOrUpdate(user);
        if (flag) {
            return R.ok().message("信息修改成功！\n").data("User", user);
        } else {
            return R.error().message("信息修改失败，请重试！");
        }

    }

    /**
     * 用户查询
     * @param info
     * @return
     */
    @GetMapping("/getUser")
    @ResponseBody
    public R getUser(String info) {

        User user = userService.findByInfo(info);
        if (user != null) {
            return R.ok().message("查询完毕！").data("User", user);
        } else {
            return R.error().message("用户不存在，请重新输入！");
        }

    }

    /**
     * 显示用户列表
     * @return
     */
    @GetMapping("/getUsers")
    @ResponseBody
    public R getUsers() {

        return R.ok().data("Users", userService.findAllUsers());

    }

    /**
     * 课程审核
     * @param pass
     * @return
     */
    @PostMapping("/checkLesson")
    @ResponseBody
    public R checkLesson(Integer lid, boolean pass) {

        Lesson lesson = lessonService.getById(lid);
        if (pass) {
            lesson.setLState(1);
            return R.ok().message("审核通过！").data("Lesson", lesson);
        } else {
            lesson.setLState(2);
            return R.error().message("审核未通过，请修改后重新提交！");
        }

    }

    /**
     * 课程删除
     * @param lid
     * @return
     */
    @GetMapping("/delLesson")
    @ResponseBody
    public R delLesson(Integer lid) {

        boolean flag = lessonService.removeById(lid);
        if (flag) {
            return R.ok().message("课程删除成功！");
        } else {
            return R.error().message("课程删除失败！");
        }

    }

    /**
     * 课程查询
     * @param info
     * @return
     */
    @GetMapping("/getLesson")
    @ResponseBody
    public R getLesson(String info) {

        Lesson lesson = lessonService.findByInfo(info);
        if (lesson != null) {
            return R.ok().message("查询完毕！").data("Lesson", lesson);
        } else {
            return R.error().message("该课程不存在，请重新输入！");
        }

    }

    /**
     * 显示某讲师的课程列表
     * @param uid
     * @return
     */
    @GetMapping("/getTeacherLessons")
    @ResponseBody
    public R getTeacherLessons(Integer uid) {

        //判断该用户是否是讲师
        if(userService.isTeacher(uid)) {
            return R.ok().data("Teacher's lessons", lessonService.findAllTeacherLessons(uid));
        } else {
            return R.error().message("该用户不是讲师，请重试！");
        }

    }

    /**
     * 显示某状态下订单列表
     * @param state
     * @return
     */
    @GetMapping("/getStateLessons")
    @ResponseBody
    public R getStateLessons(Integer state) {

        switch (state) {
            case 0:
                return R.ok().data("未审核课程", lessonService.findAllLessonsOfState(0));
            case 1:
                return R.ok().data("已通过课程", lessonService.findAllLessonsOfState(1));
            case 2:
                return R.ok().data("未通过课程", lessonService.findAllLessonsOfState(2));
            default:
                return R.error().message("状态不存在，请重试！");
        }

    }

    /**
     * 显示课程列表
     * @return
     */
    @GetMapping("/getLessons")
    @ResponseBody
    public R getLessons() {

        return R.ok().data("Lessons", lessonService.findAllLessons());

    }

    /**
     * 订单查询
     * @param oid
     * @return
     */
    @GetMapping("/getIndent")
    @ResponseBody
    public R getIndent(Integer oid) {

        Indent indent = indentService.getById(oid);
        if(indent != null) {
            return R.ok().message("订单查询完毕！\n").data("Indent", indent);
        } else {
            return R.error().message("订单不存在，请重试！");
        }

    }

    //按日期显示订单列表

    /**
     * 显示某用户的订单列表
     * @param uid
     * @return
     */
    @GetMapping("/getUserIndents")
    @ResponseBody
    public R getUserIndents(Integer uid) {

        return R.ok().data("User's Indents", indentService.findAllUserIndents(uid));

    }

    /**
     * 显示订单列表
     * @return
     */
    @GetMapping("/getIndents")
    @ResponseBody
    public R getOrders() {

        return R.ok().data("Indents", indentService.findAllIndents());

    }

}