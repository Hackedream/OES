package com.wakuwaku.oes5.service;

import com.wakuwaku.oes5.utils.result.R;

import java.util.Map;

/**
 * 支付业务实现类
 *
 * @author kou
 */
public interface IPayService {

    /**
     * 生成支付宝收款二维码
     *
     * @param userId  用户id
     * @param amount  金额
     * @param subject 主体
     * @return 二维码地址
     */
    R alipayQrcode(Long userId, String amount, String subject);

    /**
     * 生成收款二维码
     *
     * @param userId  用户id
     * @param amount  金额
     * @param subject 主体
     * @param ip      IP地址
     * @return 二维码地址
     */
    R wechatPayQrcode(Long userId, String amount, String subject, String ip);

    /**
     * 查询支付宝订单信息
     *
     * @param tradeNo    支付宝交易号，和商户订单号不能同时为空
     * @param outTradeNo 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。trade_no,out_trade_no如果同时存在优先取trade_no
     * @return 查询结果
     */
    R queryAlipay(String tradeNo, String outTradeNo);

    /**
     * 查询微信订单信息
     *
     * @param transactionId    微信订单号，和商户订单号不能同时为空
     * @param outTradeNo 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。trade_no,out_trade_no如果同时存在优先取trade_no
     * @return 查询结果
     */
    R queryWechatPay(String transactionId, String outTradeNo);

    /**
     * 支付宝异步通知处理数据
     *
     * @param params 通知数据
     * @return 结果
     */
    String alipayNotifyDealWithData(Map<String, String> params);

    /**
     * 微信异步通知处理数据
     *
     * @param xml 通知数据
     * @return 结果
     */
    String wechatPayNotifyDealWithData(String xml);

}
