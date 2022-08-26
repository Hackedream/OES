package com.wakuwaku.oes5.service;

import com.wakuwaku.oes5.entity.Indent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
public interface IIndentService extends IService<Indent> {

    Indent createIndent(Integer uid, Integer lid, boolean bought);

    List<Indent> findAllUserIndents(Integer uid);

    List<Indent> findAllIndents();

}
