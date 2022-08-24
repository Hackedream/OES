package com.wakuwaku.oes3.controller;


import com.wakuwaku.oes3.entity.Comment;
import com.wakuwaku.oes3.entity.Question;
import com.wakuwaku.oes3.service.IQuestionService;
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
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    @Resource
    IQuestionService questionService;

    /**
     * 问题添加
     * @param question
     * @return
     */
    @PostMapping("/addQuestion")
    @ResponseBody
    public R addQuestion(@RequestBody Question question) {

        //判断内容是否含有不良信息

        boolean flag = questionService.save(question);
        if (flag) {
            return R.ok().message("问题添加成功！\n").data("Question", question);
        } else {
            return R.error().message("问题添加失败！");
        }

    }

    /**
     * 问题删除
     * @param qid
     * @return
     */
    @GetMapping("/delQuestion")
    @ResponseBody
    public R delQuestion(Integer qid) {

        boolean flag = questionService.removeById(qid);
        if (flag) {
            return R.ok().message("问题删除成功！");
        } else {
            return R.error().message("问题删除失败！");
        }

    }

}
