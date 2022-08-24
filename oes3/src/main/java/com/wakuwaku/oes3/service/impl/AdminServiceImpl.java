package com.wakuwaku.oes3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes3.entity.Admin;
import com.wakuwaku.oes3.mapper.AdminMapper;
import com.wakuwaku.oes3.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public Admin login(String account, String password) {

        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("aAccount", account);
        adminQueryWrapper.eq("aPassword", password);
        Admin admin = adminMapper.selectOne(adminQueryWrapper);
        if(admin == null) {
            try {
                throw new NotFoundException("该账号不存在或密码错误！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return admin;

    }
}
