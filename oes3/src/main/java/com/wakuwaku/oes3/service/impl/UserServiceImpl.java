package com.wakuwaku.oes3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wakuwaku.oes3.entity.Lesson;
import com.wakuwaku.oes3.entity.User;
import com.wakuwaku.oes3.mapper.UserMapper;
import com.wakuwaku.oes3.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;
    Page<User> userPage;

    @Override
    public User checkByTel(String tel, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uTel", tel);
        userQueryWrapper.eq("uPassword", password);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null) {
            try {
                throw new NotFoundException("该手机号不存在或密码错误！");
            }catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User checkByEmail(String email, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uEmail", email);
        userQueryWrapper.eq("uPassword", password);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null) {
            try {
                throw  new NotFoundException("该邮箱不存在或密码错误！");
            }catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User checkByUsername(String username, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        userQueryWrapper.eq("uPassword", password);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null) {
            try {
                throw  new NotFoundException("该用户名不存在或密码错误！");
            }catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User findByTel(String tel) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uTel", tel);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null) {
            try {
                throw  new NotFoundException("该用户名不存在！");
            }catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uEmail", email);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null) {
            try {
                throw  new NotFoundException("该用户名不存在！");
            }catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User findByName(String name) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uName", name);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null) {
            try {
                throw  new NotFoundException("该用户名不存在！");
            }catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {

        QueryWrapper wrapper = new QueryWrapper();
        List<User> users = this.baseMapper.selectList(wrapper);
        return users;

    }

    @Override
    public boolean isTeacher(Integer uid) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uid", uid);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user.getUType()) {
            return true;
        } else {
            return false;
        }
    }

}
