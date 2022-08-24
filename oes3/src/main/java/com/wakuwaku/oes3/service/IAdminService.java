package com.wakuwaku.oes3.service;

import com.wakuwaku.oes3.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
public interface IAdminService extends IService<Admin> {

    Admin login(String account, String password);
}
