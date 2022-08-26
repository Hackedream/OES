package com.wakuwaku.oes6.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes6.entity.User;
import com.wakuwaku.oes6.mapper.UserMapper;
import com.wakuwaku.oes6.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean infoIsCorrect(User user) {

        //手机号和邮箱符合命名规则
        String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String tel = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[56])|(17[0-8])|(18[0-9])|(19[1589]))\\d{8}$";

        //手机号重复
        if (!user.getUTel().matches(tel)) {
            try {
                throw new Exception("手机号有误，请检查后重新输入！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            QueryWrapper<User> telWrapper = new QueryWrapper<>();
            telWrapper.eq("uTel", telWrapper);
            if(userMapper.selectOne(telWrapper) != null) {
                try {
                    throw new Exception("手机号已被注册，请重新输入！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }

        //邮箱重复
        if (!user.getUEmail().matches(email)) {
            try {
                throw new Exception("邮箱输入有误，请检查后重新输入！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            QueryWrapper<User> emailWrapper = new QueryWrapper<>();
            emailWrapper.eq("uEmail", emailWrapper);
            if(userMapper.selectOne(emailWrapper) != null) {
                try {
                    throw new Exception("邮箱已被注册，请重新输入！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }

        //用户名重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername());
        if(userMapper.selectOne(userQueryWrapper) != null) {
            try {
                throw new Exception("用户名已被使用，请重新输入！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;

    }

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
    public boolean delConfirm() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请确认是否注销账号：（是请输入yes）");
        String confirm = scanner.nextLine();
        if (confirm == "yes" || confirm == "Yes" || confirm == "YES") {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean payConfirm(Integer uid, Integer payPassword) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("uid", uid);
        userQueryWrapper.eq("uPayPassword", payPassword);
        if (userMapper.selectOne(userQueryWrapper) == null) {
            try {
                throw new NotFoundException("密码错误！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            return true;
        }

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
    public User findByUsername(String username) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
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

    @Override
    public User findByInfo(String info) {

        //判断是邮箱还是手机号的正则表达式
        String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String tel = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[56])|(17[0-8])|(18[0-9])|(19[1589]))\\d{8}$";

        //通过手机号查询
        if (info.matches(tel)) {
            User userTel = this.findByTel(info);
            if (userTel != null) {
                return userTel;
            }
        }

        //通过邮箱查询
        if (info.matches(email)) {
            User userEmail = this.findByEmail(info);
            if (userEmail != null) {
                return userEmail;
            }
        }

        //通过用户名查询
        User user = this.findByUsername(info);
        if (user != null) {
            return user;
        }

        //通过id查询
        user = this.getById(info);
        return user;

    }

    @Override
    public User login(String account, String password) {

        User user = new User();

        //判断是邮箱还是手机号的正则表达式
        String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String tel = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[56])|(17[0-8])|(18[0-9])|(19[1589]))\\d{8}$";

        //手机号登录
        if (account.matches(tel)) {
            user = this.checkByTel(account, password);
            if (user != null) {
                return user;
            }
        }

        //邮箱登录
        else if (account.matches(email)) {
            user = this.checkByEmail(account, password);
            if (user != null) {
                return user;
            }
        }

        //用户名登录（默认用户名不可重复）
        else {
            user = this.checkByUsername(account, password);
            return user;
        }

        return null;

        //若用户名可重复，则只可通过手机号与邮箱登录
        /*else {
            return R.error().message("该用户不存在或密码错误！");
        }*/
    }

    @Override
    public List<User> findAllByUids(List<Integer> uids) {

        List<User> users = userMapper.selectBatchIds(uids);
        if (users == null) {
            try {
                throw new NotFoundException("未查询到用户！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return users;

    }

    @Override
    public List<User> findTeachersByKey(String keyword) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("uName", keyword);
        userQueryWrapper.eq("uType", 1);
        List<User> teachers = userMapper.selectList(userQueryWrapper);
        if (teachers != null) {
            return teachers;
        } else {
            try {
                throw new NotFoundException("未查询到结果！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    @Override
    public User uploadPortrait(Integer uid, String url) {

        return getById(uid).setUPortrait(url);

    }

    @Override
    public User uploadTeacherPhoto(Integer uid, String url) {

        return getById(uid).setUTeacherPhoto(url);

    }

}
