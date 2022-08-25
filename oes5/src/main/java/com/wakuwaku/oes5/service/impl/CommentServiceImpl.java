package com.wakuwaku.oes5.service.impl;

import com.wakuwaku.oes5.entity.Comment;
import com.wakuwaku.oes5.mapper.CommentMapper;
import com.wakuwaku.oes5.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
