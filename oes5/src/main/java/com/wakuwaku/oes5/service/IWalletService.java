package com.wakuwaku.oes5.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundTransPayModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wakuwaku.oes5.entity.WechatPay;
import com.wakuwaku.oes5.entity.WithdrawApply;
import com.wakuwaku.oes5.utils.result.R;

import java.math.BigDecimal;
import java.util.List;

/**
 * 钱包业务接口
 *
 * @author kou
 */
public interface IWalletService {

    /**
     * 微信提现
     *
     * @param userId    用户id
     * @param wechatPay 提现信息
     * @return 结果
     */
    R wechatWithdraw(Long userId, WechatPay wechatPay) throws JsonProcessingException;

    /**
     * 支付宝提现
     *
     * @param userId           用户id
     * @param withdrawApply 提现申请信息
     * @param model            提现信息
     * @return 结果
     */
    R alipayWithdraw(Long userId, WithdrawApply withdrawApply, AlipayFundTransPayModel model) throws JsonProcessingException, AlipayApiException;

}
