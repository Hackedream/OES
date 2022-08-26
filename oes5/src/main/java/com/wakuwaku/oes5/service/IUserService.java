package com.wakuwaku.oes5.service;

import com.wakuwaku.oes5.entity.User;
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
public interface IUserService extends IService<User> {

    boolean infoIsCorrect(User user);

    User checkByTel(String tel, String password);

    User checkByEmail(String email, String password);

    User checkByUsername(String username, String password);

    boolean delConfirm();

    boolean payConfirm(Integer uid, Integer payPassword);

    User findByTel(String tel);

    User findByEmail(String email);

    User findByUsername(String name);

    List<User> findAllUsers();

    boolean isTeacher(Integer uid);

    User findByInfo(String info);

    User login(String account, String password);

    List<User> findAllByUids(List<Integer> uids);
}
