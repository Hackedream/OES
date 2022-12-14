package com.wakuwaku.oes3.service.impl;

import com.wakuwaku.oes3.entity.Comment;
import com.wakuwaku.oes3.mapper.CommentMapper;
import com.wakuwaku.oes3.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
