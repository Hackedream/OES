package com.wakuwaku.oes3.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.wakuwaku.oes3.entity.Order;
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
public interface IOrderService extends IService<Order> {

    List<Order> findAllOrders();

    List<Order> findAllUserOrders(Integer uid);

    Order createOrder(Integer uid, Integer lid);
}
