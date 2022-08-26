package com.wakuwaku.oes5.service.impl;

import com.wakuwaku.oes5.entity.Checklist;
import com.wakuwaku.oes5.mapper.ChecklistMapper;
import com.wakuwaku.oes5.service.IChecklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wakuwaku.oes5.service.ILessonService;
import com.wakuwaku.oes5.service.IUserService;
import com.wakuwaku.oes5.service.IWebmasterService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
@Service
public class ChecklistServiceImpl extends ServiceImpl<ChecklistMapper, Checklist> implements IChecklistService {

    @Resource
    ChecklistMapper checklistMapper;
    @Resource
    IWebmasterService webmasterService;
    @Resource
    ILessonService lessonService;

    @Override
    public Checklist addCheckList(Integer wid, Integer lid, boolean pass) {

        Checklist checklist = new Checklist();
        checklist.setChaid(wid);
        checklist.setChaName(webmasterService.getById(wid).getWName());
        checklist.setChlid(lid);
        checklist.setChlName(lessonService.getById(lid).getLName());
        checklist.setChuid(lessonService.getById(lid).getLuid());
        checklist.setChuName(lessonService.getById(lid).getLuName());
        checklist.setChState(pass);
        return checklist;

    }
}
