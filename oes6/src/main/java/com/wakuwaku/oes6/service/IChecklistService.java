package com.wakuwaku.oes6.service;

import com.wakuwaku.oes6.entity.Checklist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
public interface IChecklistService extends IService<Checklist> {

    Checklist addCheckList(Integer wid, Integer lid, Boolean pass);

}
