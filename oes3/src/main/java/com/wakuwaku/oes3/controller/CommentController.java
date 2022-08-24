package com.wakuwaku.oes3.controller;


import com.wakuwaku.oes3.entity.Comment;
import com.wakuwaku.oes3.service.ICommentService;
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
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Resource
    ICommentService commentService;

    /**
     * 评论添加
     * @param comment
     * @return
     */
    @PostMapping("/addComment")
    @ResponseBody
    public R addComment(@RequestBody Comment comment) {

        //判断内容是否含有不良信息

        boolean flag = commentService.save(comment);
        if (flag) {
            return R.ok().message("评论添加成功！\n").data("Comment", comment);
        } else {
            return R.error().message("评论添加失败！");
        }

    }

    /**
     * 评论删除
     * @param cid
     * @return
     */
    @GetMapping("/delComment")
    @ResponseBody
    public R delComment(Integer cid) {

        boolean flag = commentService.removeById(cid);
        if (flag) {
            return R.ok().message("评论删除成功！");
        } else {
            return R.error().message("评论删除失败！");
        }

    }

}
