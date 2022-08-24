package com.wakuwaku.oes3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wakuwaku.oes3.entity.Lesson;
import com.wakuwaku.oes3.entity.Order;
import com.wakuwaku.oes3.entity.User;
import com.wakuwaku.oes3.mapper.LessonMapper;
import com.wakuwaku.oes3.mapper.OrderMapper;
import com.wakuwaku.oes3.service.ILessonService;
import com.wakuwaku.oes3.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wakuwaku.oes3.service.IUserService;
import io.swagger.models.auth.In;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    OrderMapper orderMapper;
    Page<Order> orderPage;
    ILessonService lessonService;
    IUserService userService;

    @Override
    public List<Order> findAllOrders() {

        QueryWrapper wrapper = new QueryWrapper();
        List<Order> orders = this.baseMapper.selectList(wrapper);
        return orders;

    }

    @Override
    public List<Order> findAllUserOrders(Integer uid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("ouid", uid);
        orderQueryWrapper.eq("ouuid", uid);
        List<Order> orders = orderMapper.selectList(orderQueryWrapper);
        if (orders == null) {
            try {
                throw new NotFoundException("该用户无任何订单！");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    @Override
    public Order createOrder(Integer uid, Integer lid) {

        Order order = new Order();
        //订单状态
        order.setOState("待支付");
        //课程信息录入
        order.setOlid(lid);
        order.setOlName(lessonService.getById(lid).getLName());
        order.setOTeaID(lessonService.getById(lid).getLuid());
        order.setOTeaUsername(lessonService.getById(lid).getLuName());
        //学生信息录入
        order.setOStuID(uid);
        order.setOStuUsername(userService.getById(uid).getUsername());
        return order;
    }


}
