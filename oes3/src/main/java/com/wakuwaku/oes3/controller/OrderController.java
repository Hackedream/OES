package com.wakuwaku.oes3.controller;


import com.sun.org.apache.xpath.internal.operations.Or;
import com.wakuwaku.oes3.entity.Order;
import com.wakuwaku.oes3.service.IOrderService;
import com.wakuwaku.oes3.utils.result.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Resource
    IOrderService orderService;

    /**
     * 订单添加
     * @param order
     * @return
     */
    @PostMapping("/addOrder")
    @ResponseBody
    public R addOrder(@RequestBody Order order) {

        boolean flag = orderService.save(order);
        if (flag) {
            return R.ok().message("订单添加成功！\n").data("Order", order);
        } else {
            return R.error().message("订单添加失败！");
        }

    }

    /**
     * 订单删除
     * @param oid
     * @return
     */
    @GetMapping("/delOrder")
    @ResponseBody
    public R delOrder(Integer oid) {

        boolean flag = orderService.removeById(oid);
        if (flag) {
            return R.ok().message("订单删除成功！");
        } else {
            return R.error().message("订单删除失败！");
        }

    }

    /**
     * 订单信息修改
     * @param order
     * @return
     */
    @PostMapping("/updateOrder")
    @ResponseBody
    public R updateOrder(@RequestBody Order order) {

        boolean flag = orderService.saveOrUpdate(order);
        if (flag) {
            return R.ok().message("订单信息修改成功！\n").data("Order", order);
        } else {
            return R.error().message("订单信息修改失败！");
        }

    }

    /**
     * 订单状态修改
     * @param oid
     * @param state
     * @return
     */
    @PostMapping("/updateOrderState")
    @ResponseBody
    public R updateOrderState(Integer oid, String state) {

        Order order = orderService.getById(oid);
        if (order != null) {
            order.setOState(state);
            return R.ok().message("订单状态修改成功！").data("State", order.getOState());
        } else {
            return R.error().message("该订单不存在，请重试！");
        }

    }

    /**
     * 订单查询
     * @param oid
     * @return
     */
    @GetMapping("/getOrder")
    @ResponseBody
    public R getOrder(Integer oid) {

        Order order = orderService.getById(oid);
        if (order != null) {
            return R.ok().message("查询完毕！\n").data("Order", order);
        } else {
            return R.error().message("订单查询失败，请重试！");
        }

    }

}
