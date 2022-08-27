package com.wakuwaku.oes6.service;

import com.wakuwaku.oes6.entity.Webmaster;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
public interface IWebmasterService extends IService<Webmaster> {

    Webmaster login(String account, String password);

}