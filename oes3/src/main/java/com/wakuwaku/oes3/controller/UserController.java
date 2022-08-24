package com.wakuwaku.oes3.controller;


import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.wakuwaku.oes3.entity.Lesson;
import com.wakuwaku.oes3.entity.Order;
import com.wakuwaku.oes3.entity.User;
import com.wakuwaku.oes3.service.ILessonService;
import com.wakuwaku.oes3.service.IOrderService;
import com.wakuwaku.oes3.service.IUserService;
import com.wakuwaku.oes3.utils.result.R;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    IUserService userService;
    ILessonService lessonService;
    IOrderService orderService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public R register(@RequestBody User user) {

        //判断内容是否与已有数据重复

        boolean flag = userService.save(user);
        if (flag) {
            return R.ok().message("注册成功！").data("User", user);
        } else {
            return R.error().message("注册失败！");
        }

    }

    /**
     * 手机号/邮箱/用户名登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(String account, String password) {

        //判断是邮箱还是手机号的正则表达式
        String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String tel = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[56])|(17[0-8])|(18[0-9])|(19[1589]))\\d{8}$";

        //手机号登录
        if (account.matches(tel)) {
            User userTel = userService.checkByTel(account, password);
            if (userTel != null) {
                return R.ok().message("登录成功！");
            } else {
                return R.error().message("登录失败！");
            }
        }

        //邮箱登录
        else if (account.matches(email)) {
            User userTel = userService.checkByEmail(account, password);
            if (userTel != null) {
                return R.ok().message("登录成功！");
            } else {
                return R.error().message("登录失败！");
            }
        }

        //用户名登录（默认用户名不可重复）
        else {
            User userTel = userService.checkByUsername(account, password);
            if (userTel != null) {
                return R.ok().message("登录成功！");
            } else {
                return R.error().message("登录失败！");
            }
        }

        //若用户名可重复，则只可通过手机号与邮箱登录
        /*else {
            return R.error().message("该用户不存在或密码错误！");
        }*/

    }

    /**
     * 个人信息修改
     * @param user
     * @return
     */
    @PostMapping("/updateSelfInfo")
    @ResponseBody
    public R updateSelfInfo(@RequestBody User user) {

        //判断内容是否含有不良信息

        //判断内容与已有数据是否冲突

        boolean flag = userService.saveOrUpdate(user);
        if (flag) {
            return R.ok().message("修改成功！");
        } else {
            return R.error().message("修改失败！");
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

        boolean flag = userService.removeById(uid);
        if (flag) {
            return R.ok().message("账号已注销！");
        } else {
            return R.error().message("账号不存在或注销失败！");
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
    public R getSelfBalance(Integer uid) {

        //输入支付密码验证身份

        return R.ok().data("Balance", userService.getById(uid).getUBalance());

    }

    /**
     * 课程添加（限讲师）
     * @param lesson
     * @return
     */
    @PostMapping("/addLesson")
    @ResponseBody
    public R addLesson(@RequestBody Lesson lesson) {

        //判断内容是否含有不良信息

        //判断关键信息是否与已有数据重复

        boolean flag = lessonService.save(lesson);
        if (flag) {
            return R.ok().message("操作成功，请等待管理员审核！");
        } else {
            return R.error().message("操作失败，请重试！");
        }

    }

    /**
     * 课程删除（限讲师）
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
     * 课程信息修改（限讲师）
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

        //创建订单
        Order order = orderService.createOrder(uid, lid);

        return R.ok().message("购买成功！");

    }

}
