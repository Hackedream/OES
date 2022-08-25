package com.wakuwaku.oes5.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes5.entity.Webmaster;
import com.wakuwaku.oes5.mapper.WebmasterMapper;
import com.wakuwaku.oes5.service.IWebmasterService;
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
 * @since 2022-08-25
 */
@Service
public class WebmasterServiceImpl extends ServiceImpl<WebmasterMapper, Webmaster> implements IWebmasterService {

    @Resource
    WebmasterMapper webmasterMapper;

    @Override
    public Webmaster login(String account, String password) {

        QueryWrapper<Webmaster> webmasterQueryWrapper = new QueryWrapper<>();
        webmasterQueryWrapper.eq("wAccount", account);
        webmasterQueryWrapper.eq("wPassword", password);
        Webmaster webmaster = webmasterMapper.selectOne(webmasterQueryWrapper);
        if (webmaster == null) {
            try {
                throw new NotFoundException("账号不存在或密码错误！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return webmaster;

    }

}
