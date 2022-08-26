package com.wakuwaku.oes6.controller;


import com.wakuwaku.oes6.entity.Indent;
import com.wakuwaku.oes6.entity.Lesson;
import com.wakuwaku.oes6.entity.Photo;
import com.wakuwaku.oes6.entity.User;
import com.wakuwaku.oes6.service.*;
import com.wakuwaku.oes6.utils.result.R;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.DataOutput;
import java.io.File;
import java.util.Date;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;
    @Resource
    ILessonService lessonService;
    @Resource
    IIndentService indentService;
    @Resource
    IUserLessonService userLessonService;
    @Resource
    ICollectLessonsService collectLessonsService;

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

    /**
     * 个人信息修改
     * @param user
     * @return
     */
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
     * 上传头像
     * @param uid
     * @param url
     * @return
     */
    @PostMapping("/uploadPortrait")
    @ResponseBody
    public R uploadPortrait(Integer uid, String url) {

        boolean flag = userService.saveOrUpdate(userService.uploadPortrait(uid, url));
        if (flag) {
            return R.ok().message("上传成功！").data("User", userService.getById(uid));
        } else {
            return R.error().message("上传失败！");
        }

    }

    /**
     * 上传讲师图片
     * @param uid
     * @param url
     * @return
     */
    @PostMapping("/uploadTeacherPhoto")
    @ResponseBody
    public R uploadTeacherPhoto(Integer uid, String url) {

        boolean flag = userService.saveOrUpdate(userService.uploadTeacherPhoto(uid, url));
        if (flag) {
            return R.ok().message("上传成功！").data("User", userService.getById(uid));
        } else {
            return R.error().message("上传失败！");
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
     * 搜索关键词
     * @param keyword
     * @return
     */
    @GetMapping("/search")
    @ResponseBody
    public R search(String keyword) {

        List<Lesson> lessons = lessonService.findLessonsByKey(keyword);
        List<User> teachers = userService.findTeachersByKey(keyword);
        if (lessons != null) {
            if (teachers != null) {
                return R.ok().message("查询完毕！").data("Lessons", lessons).data("Teachers", teachers);
            } else {
                return R.ok().message("查询完毕！").data("Lessons", lessons);
            }
        } else {
            if (teachers != null) {
                return R.ok().message("查询完毕！").data("Teachers", teachers);
            } else {
                return R.error().message("未查询到结果！");
            }
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
     * 上传课程视频
     * @param lid
     * @param url
     * @return
     */
    @PostMapping("/uploadLessonVideo")
    @ResponseBody
    public R uploadLessonVideo(Integer lid, String url) {

        boolean flag = lessonService.saveOrUpdate(lessonService.uploadLessonVideo(lid, url));
        if (flag) {
            return R.ok().message("上传成功！").data("Lesson", lessonService.getById(lid));
        } else {
            return R.error().message("上传失败！");
        }

    }

    /**
     * 上传课程音频
     * @param lid
     * @param url
     * @return
     */
    @PostMapping("/uploadLessonAudio")
    @ResponseBody
    public R uploadLessonAudio(Integer lid, String url) {

        boolean flag = lessonService.saveOrUpdate(lessonService.uploadLessonAudio(lid, url));
        if (flag) {
            return R.ok().message("上传成功！").data("Lesson", lessonService.getById(lid));
        } else {
            return R.error().message("上传失败！");
        }

    }

    /**
     * 上传课程图片
     * @param lid
     * @param url
     * @return
     */
    @PostMapping("/uploadLessonPhoto")
    @ResponseBody
    public R uploadLessonPhoto(Integer lid, String url) {

        boolean flag = lessonService.saveOrUpdate(lessonService.uploadLessonPhoto(lid, url));
        if (flag) {
            return R.ok().message("上传成功！").data("Lesson", lessonService.getById(lid));
        } else {
            return R.error().message("上传失败！");
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
     * 通过id进行课程信息查询
     * @param lid
     * @return
     */
    @GetMapping("/getLessonInfoById")
    @ResponseBody
    public R getLessonInfoById(Integer lid) {

        Lesson lesson = lessonService.getById(lid);
        if (lesson != null) {
            return R.ok().data("Lesson", lesson);
        } else {
            return R.error();
        }

    }

    /**
     * 通过关键词进行课程搜索
     * @param keyword
     * @return
     */
    @GetMapping("/searchLessons")
    @ResponseBody
    public R getLessonsInfo(String keyword) {

        List<Lesson> lessons = lessonService.findLessonsByKey(keyword);
        if (lessons != null) {
            return R.ok().message("课程查询完毕！").data("Lessons", lessons);
        } else {
            return R.error().message("未查询到课程！");
        }

    }

    /**
     * 通过类别对课程进行筛选
     * @param category
     * @return
     */
    @GetMapping("/getLessonsByCategory")
    @ResponseBody
    public R getLessonsByCategory(Integer category) {

        List<Lesson> lessons = lessonService.findLessonsByCategory(category);
        if (lessons != null) {
            return R.ok().message("课程查询完毕！").data("Lessons", lessons);
        } else {
            return R.error().message("未查询到课程！");
        }

    }

    /**
     * 通过价格区间对课程进行筛选
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @GetMapping("/getLessonsByPriceSection")
    @ResponseBody
    public R getLessonsByPriceSection(Double minPrice, Double maxPrice) {

        List<Lesson> lessons = lessonService.findLessonsByPriceSection(minPrice, maxPrice);
        if (lessons != null) {
            return R.ok().message("课程查询完毕！").data("Lessons", lessons);
        } else {
            return R.error().message("未查询到课程！");
        }

    }

    /**
     * 查询某讲师所有课程
     * @param uid
     * @return
     */
    @GetMapping("/getTeacherLessons")
    @ResponseBody
    public R getTeacherLessons(Integer uid) {

        List<Lesson> lessons = lessonService.findAllTeacherLessons(uid);
        if (lessons != null) {
            return R.ok().message("查询完毕！").data("Lessons", lessons);
        } else {
            return R.error().message("未查询到结果！");
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
    public R buyLesson(Integer uid, Integer lid, boolean bought) {

        Indent indent = indentService.createIndent(uid, lid, bought);
        if (indent != null) {
            indentService.saveOrUpdate(indent);
            if (bought) {
                return R.ok().message("购买成功！").data("Indent", indent);
            } else {
                return R.ok().message("支付已取消！");
            }
        } else {
            return R.error().message("购买失败，请重试！");
        }

    }

    /**
     * 已购买课程查询
     * @param uid
     * @return
     */
    @GetMapping("/getUserLessons")
    @ResponseBody
    public R getUserLessons(Integer uid) {

        List<Lesson> lessons = userLessonService.getSameUserLessons(uid);
        if (lessons != null) {
            return R.ok().message("购买课程如下：").data("Lessons", lessons);
        } else {
            return R.error().message("您未购买任何课程！");
        }

    }

    /**
     * 已收藏课程查询
     * @param uid
     * @return
     */
    @GetMapping("/getCollectLessons")
    @ResponseBody
    public R getCollectLessons(Integer uid) {

        List<Lesson> lessons = collectLessonsService.getSameUserLessons(uid);
        if (lessons != null) {
            return R.ok().message("收藏课程如下：").data("Lessons", lessons);
        } else {
            return R.error().message("您未收藏任何课程！");
        }

    }

    /**
     * 已上传课程查询
     * 仅限讲师操作
     * @param uid
     * @return
     */
    @GetMapping("/getUploadLessons")
    @ResponseBody
    public R getUploadLessons(Integer uid) {

        //判断是否为讲师
        if (userService.isTeacher(uid)) {
            List<Lesson> lessons = lessonService.findAllTeacherLessons(uid);
            if (lessons != null) {
                return R.ok().message("查询完毕！").data("Lessons", lessons);
            } else {
                return R.error().message("您未上传任何课程！");
            }
        } else {
            return R.error().message("您不是讲师！");
        }

    }

}
