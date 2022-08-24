package com.wakuwaku.oes3.controller;


import com.wakuwaku.oes3.entity.Answer;
import com.wakuwaku.oes3.service.IAnswerService;
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
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController {

    @Resource
    IAnswerService answerService;

    /**
     * 回答添加
     * @param answer
     * @return
     */
    @PostMapping("/addAnswer")
    @ResponseBody
    public R addAnswer(@RequestBody Answer answer) {

        //判断内容是否含有不良信息

        boolean flag = answerService.save(answer);
        if (flag) {
            return R.ok().message("回答添加成功！\n").data("Answer", answer);
        } else {
            return R.error().message("回答添加失败！");
        }

    }

    /**
     * 回答删除
     * @param anid
     * @return
     */
    @GetMapping("/delAnswer")
    @ResponseBody
    public R delAnswer(Integer anid) {

        boolean flag = answerService.removeById(anid);
        if (flag) {
            return R.ok().message("回答删除成功！");
        } else {
            return R.error().message("回答删除失败！");
        }

    }

}
