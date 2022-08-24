package com.wakuwaku.oes3.service.impl;

import com.wakuwaku.oes3.entity.Question;
import com.wakuwaku.oes3.mapper.QuestionMapper;
import com.wakuwaku.oes3.service.IQuestionService;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
