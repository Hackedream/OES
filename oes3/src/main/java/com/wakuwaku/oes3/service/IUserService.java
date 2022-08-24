package com.wakuwaku.oes3.service;

import com.wakuwaku.oes3.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
public interface IUserService extends IService<User> {

    User checkByTel(String account, String password);

    User checkByEmail(String account, String password);

    User checkByUsername(String account, String password);

    User findByTel(String tel);

    User findByEmail(String email);

    User findByName(String name);

    List<User> findAllUsers();

    boolean isTeacher(Integer uid);
}
