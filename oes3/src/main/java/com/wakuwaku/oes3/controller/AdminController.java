package com.wakuwaku.oes3.controller;


import com.wakuwaku.oes3.entity.Admin;
import com.wakuwaku.oes3.entity.Lesson;
import com.wakuwaku.oes3.entity.Order;
import com.wakuwaku.oes3.entity.User;
import com.wakuwaku.oes3.service.IAdminService;
import com.wakuwaku.oes3.service.ILessonService;
import com.wakuwaku.oes3.service.IOrderService;
import com.wakuwaku.oes3.service.IUserService;
import com.wakuwaku.oes3.utils.result.R;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.LessOrEqualToExpression;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Resource
    IUserService userService;
    @Resource
    IAdminService adminService;
    @Resource
    ILessonService lessonService;
    @Resource
    IOrderService orderService;

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(String account, String password) {

        Admin admin = adminService.login(account, password);
        if (admin != null) {
            return R.ok().message("登录成功！\n").data("Admin", admin);
        } else {
            return R.error().message("该用户不存再或密码错误，请重试！");
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

        //判断是邮箱还是手机号的正则表达式
        String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String tel = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[56])|(17[0-8])|(18[0-9])|(19[1589]))\\d{8}$";

        //通过手机号查询
        if (info.matches(tel)) {
            User userTel = userService.findByTel(info);
            if (userTel != null) {
                return R.ok().message("查询完毕！\n").data("User", userTel);
            }
        }

        //通过邮箱查询
        if (info.matches(email)) {
            User userEmail = userService.findByEmail(info);
            if (userEmail != null) {
                return R.ok().message("查询完毕！\n").data("User", userEmail);
            }
        }

        //通过用户名查询
        User user = userService.findByName(info);
        if (user != null) {
            return R.ok().message("查询完毕！\n").data("User", user);
        }

        //通过id查询
        user = userService.getById(info);
        if (user != null) {
            return R.ok().message("查询完毕！\n").data("User", user);
        } else {
            return R.error().message("该用户不存在，请重试！");
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
    public R checkLesson(boolean pass) {

        if (pass) {
            return R.ok().message("审核通过！");
        } else {
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

        //通过课程名查询
        Lesson lesson = lessonService.findByName(info);
        if (lesson != null) {
            return R.ok().message("查询完毕！\n").data("Lesson", lesson);
        }

        //通过id查询
        lesson = lessonService.getById(info);
        if (lesson != null) {
            return R.ok().message("查询完毕！\n").data("Lesson", lesson);
        } else {
            return R.error();
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
    @GetMapping("/getOrder")
    @ResponseBody
    public R getOrder(Integer oid) {

        Order order = orderService.getById(oid);
        if(order != null) {
            return R.ok().message("订单查询完毕！\n").data("Order", order);
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
    @GetMapping("/GetUserOrders")
    @ResponseBody
    public R getUserOrders(Integer uid) {

        return R.ok().data("User's orders", orderService.findAllUserOrders(uid));

    }

    /**
     * 显示订单列表
     * @return
     */
    @GetMapping("/getOrders")
    @ResponseBody
    public R getOrders() {

        return R.ok().data("Orders", orderService.findAllOrders());

    }
}
