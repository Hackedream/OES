package com.wakuwaku.oes6.service.impl;

import com.wakuwaku.oes6.entity.Checklist;
import com.wakuwaku.oes6.mapper.ChecklistMapper;
import com.wakuwaku.oes6.service.IChecklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wakuwaku.oes6.service.ILessonService;
import com.wakuwaku.oes6.service.IWebmasterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
@Service
public class ChecklistServiceImpl extends ServiceImpl<ChecklistMapper, Checklist> implements IChecklistService {

    @Resource
    IWebmasterService webmasterService;
    @Resource
    ILessonService lessonService;

    @Override
    public Checklist addCheckList(Integer wid, Integer lid, Boolean pass) {

        Checklist checklist = new Checklist();
        checklist.setChaid(wid);
        checklist.setChaName(webmasterService.getById(wid).getWName());
        checklist.setChlid(lid);
        checklist.setChlName(lessonService.getById(lid).getLName());
        checklist.setChuid(lessonService.getById(lid).getLuid());
        checklist.setChuName(lessonService.getById(lid).getLuName());
        if (pass) {
            checklist.setChState(1);
        } else {
            checklist.setChState(2);
        }
        return checklist;

    }

}
