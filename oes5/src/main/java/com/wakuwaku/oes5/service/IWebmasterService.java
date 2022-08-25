package com.wakuwaku.oes5.service;

import com.wakuwaku.oes5.entity.Webmaster;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-25
 */
public interface IWebmasterService extends IService<Webmaster> {

    Webmaster login(String account, String password);
}
