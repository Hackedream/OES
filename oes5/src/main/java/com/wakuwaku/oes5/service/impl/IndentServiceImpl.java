package com.wakuwaku.oes5.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakuwaku.oes5.entity.Indent;
import com.wakuwaku.oes5.mapper.IndentMapper;
import com.wakuwaku.oes5.service.IIndentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wakuwaku.oes5.service.ILessonService;
import com.wakuwaku.oes5.service.IUserService;
import io.swagger.models.auth.In;
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
 * @since 2022-08-25
 */
@Service
public class IndentServiceImpl extends ServiceImpl<IndentMapper, Indent> implements IIndentService {

    @Resource
    IndentMapper indentMapper;
    @Resource
    IUserService userService;
    @Resource
    ILessonService lessonService;

    @Override
    public Indent createIndent(Integer uid, Integer lid, boolean bought) {

        Indent indent = new Indent();
        indent.setInStuID(uid);
        indent.setInStuUsername(userService.getById(uid).getUsername());
        indent.setInlid(lid);
        indent.setInlName(lessonService.getById(lid).getLName());
        indent.setInTeaID(lessonService.getById(lid).getLuid());
        indent.setInTeaUsername(lessonService.getById(lid).getLuName());
        indent.setInState(bought);
        return indent;

    }

    @Override
    public List<Indent> findAllUserIndents(Integer uid) {

        QueryWrapper<Indent> indentQueryWrapper = new QueryWrapper<>();
        indentQueryWrapper.eq("inStuid", uid).or().eq("inTeaid", uid);
        List<Indent> indents = indentMapper.selectList(indentQueryWrapper);
        if (indents == null) {
            try {
                throw new NotFoundException("该用户无任何订单！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return indents;

    }

    @Override
    public List<Indent> findAllIndents() {

        QueryWrapper wrapper = new QueryWrapper();
        List<Indent> indents = this.baseMapper.selectList(wrapper);
        return indents;

    }

}
