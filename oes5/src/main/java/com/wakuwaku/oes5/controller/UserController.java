package com.wakuwaku.oes5.controller;


import com.wakuwaku.oes5.entity.Indent;
import com.wakuwaku.oes5.entity.Lesson;
import com.wakuwaku.oes5.entity.User;
import com.wakuwaku.oes5.service.IIndentService;
import com.wakuwaku.oes5.service.ILessonService;
import com.wakuwaku.oes5.service.IUserService;
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
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    IUserService userService;
    @Resource
    ILessonService lessonService;
    @Resource
    IIndentService indentService;

    /**
     * 用户注册
     * 用户名、手机号、邮箱不可重复注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public R register(@RequestBody User user) {

        //判断内容是否与已有数据重复
        if (userService.infoIsCorrect(user)) {
            boolean flag = userService.save(user);
            if (flag) {
                return R.ok().message("注册成功！").data("User", user);
            } else {
                return R.error().message("注册失败，请重试！");
            }
        } else {
            return R.error().message("有信息已被使用，请重新输入！");
        }

    }

    /**
     * 用户登录
     * 可通过用户名、手机号、邮箱登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(String account, String password) {

        User user = userService.login(account, password);
        if (user != null) {
            return R.ok().message("登录成功！").data("User",user);
        } else {
            return R.error().message("账号不存在或密码错误，请重新输入！");
        }

    }

    @PostMapping("/updateSelfInfo")
    @ResponseBody
    public R updateSelfInfo(@RequestBody User user) {

        boolean flag = userService.saveOrUpdate(user);
        if (flag) {
            return R.ok().message("个人信息修改成功！").data("User", user);
        } else {
            return R.error().message("个人信息修改失败，请重试！");
        }

    }

    /**
     * 账号注销
     * @param uid
     * @return
     */
    @GetMapping("/delAccount")
    @ResponseBody
    public R delAccount(Integer uid) {

        //再次提醒，再次确认
        if (userService.delConfirm()) {
            boolean flag = userService.removeById(uid);
            if (flag) {
                return R.ok().message("账号已注销！");
            } else {
                return R.error().message("账号不存在或注销失败！");
            }
        } else {
            return R.ok().message("已取消注销！");
        }

    }

    /**
     * 个人信息查询
     * @param uid
     * @return
     */
    @GetMapping("/selfInfo")
    @ResponseBody
    public R selfInfo(Integer uid) {

        return R.ok().data("User", userService.getById(uid));

    }

    /**
     * 余额查询
     * @param uid
     * @return
     */
    @GetMapping("/getSelfBalance")
    @ResponseBody
    public R getSelfBalance(Integer uid, Integer payPassword) {

        //输入支付密码验证身份
        if (userService.payConfirm(uid, payPassword)) {
            return R.ok().data("Balance", userService.getById(uid).getUBalance());
        } else {
            return R.error().message("密码错误，请重新输入！");
        }

    }

    /**
     * 课程添加
     * 仅用户类型为讲师可操作
     * @param lesson
     * @return
     */
    @PostMapping("/addLesson")
    @ResponseBody
    public R addLesson(@RequestBody Lesson lesson) {

        //判断内容是否与已有数据重复
        if (lessonService.infoIsCorrect(lesson)) {
            boolean flag = lessonService.save(lesson);
            if (flag) {
                return R.ok().message("操作成功，请等待管理员审核！");
            } else {
                return R.error().message("操作失败，请重试！");
            }
        } else {
            return R.error().message("信息已被使用，请重新输入！");
        }

    }

    /**
     * 课程删除
     * 仅用户类型为讲师可操作
     * @param lid
     * @return
     */
    @GetMapping("/delLesson")
    @ResponseBody
    public R delLesson(Integer lid) {

        boolean flag = lessonService.removeById(lid);
        if (flag) {
            return R.ok().message("操作成功，请等待管理员审核！");
        } else {
            return R.error().message("操作失败，请重试！");
        }

    }

    /**
     * 课程信息修改
     * 仅用户类型为讲师可操作
     * @param lesson
     * @return
     */
    @PostMapping("/updateLessonInfo")
    @ResponseBody
    public R updateLessonInfo(@RequestBody Lesson lesson) {

        boolean flag = lessonService.saveOrUpdate(lesson);
        if (flag) {
            return R.ok().message("操作成功，请等待管理员审核！");
        } else {
            return R.error().message("操作失败，请重试！");
        }

    }

    /**
     * 课程信息查询
     * @param lid
     * @return
     */
    @GetMapping("/getLessonInfo")
    @ResponseBody
    public R getLessonInfo(Integer lid) {

        Lesson lesson = lessonService.getById(lid);
        if (lesson != null) {
            return R.ok().data("Lesson", lesson);
        } else {
            return R.error();
        }

    }

    /**
     * 课程购买
     * @param uid
     * @param lid
     * @return
     */
    @PostMapping("/buyLesson")
    @ResponseBody
    public R buyLesson(Integer uid, Integer lid) {

        Indent indent = indentService.createIndent(uid, lid);
        if (indent != null) {
            return R.ok().message("购买成功！").data("Indent", indent);
        } else {
            return R.error().message("购买失败，请重试！");
        }

    }

}
