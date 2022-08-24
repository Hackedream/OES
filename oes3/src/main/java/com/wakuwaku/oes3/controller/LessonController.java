package com.wakuwaku.oes3.controller;


import com.wakuwaku.oes3.entity.Lesson;
import com.wakuwaku.oes3.service.ILessonService;
import com.wakuwaku.oes3.utils.result.R;
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
@RequestMapping("/lesson")
@CrossOrigin
public class LessonController {

    @Resource
    ILessonService lessonService;

    /**
     * 课程添加
     * @param lesson
     * @return
     */
    @PostMapping("/addLesson")
    @ResponseBody
    public R addLesson(@RequestBody Lesson lesson) {

        //判断内容是否含有不良信息

        //判断内容是否与已有数据冲突


        if (lessonService.save(lesson)) {
            return R.ok().message("课程添加成功！\n").data("Lesson", lesson);
        } else {
            return R.error().message("课程添加失败！");
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
     * 课程信息修改
     * @param lesson
     * @return
     */
    @PostMapping("/updateLesson")
    @ResponseBody
    public R updateLesson(@RequestBody Lesson lesson) {

        //判断内容是否含有不良信息

        //判断内容是否与已有数据冲突


        boolean flag = lessonService.saveOrUpdate(lesson);
        if (flag) {
            return R.ok().message("课程信息修改成功！\n").data("Lesson", lesson);
        } else {
            return R.error().message("课程信息修改失败，请重试！");
        }

    }

    /**
     * 课程信息查询
     * @param info
     * @return
     */
    @GetMapping("/getLesson")
    @ResponseBody
    public R getLesson(String info) {

        //通过课程名查询
        Lesson lesson = lessonService.findByName(info);
        if (lesson != null) {
            return R.ok().message("课程信息查询成功！\n").data("Lesson", lesson);
        }

        //通过id查询
        lesson = lessonService.getById(info);
        if (lesson != null) {
            return R.ok().message("课程信息查询成功！\n").data("Lesson", lesson);
        } else {
            return R.error().message("课程信息查询失败！");
        }

    }

}
