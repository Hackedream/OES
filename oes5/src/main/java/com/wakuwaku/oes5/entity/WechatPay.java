package com.wakuwaku.oes5.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 微信支付
 * @author Ania
 */
@Data
public class WechatPay {


    /**
     * 商户订单号, 只能是字母或者数字
     */
    private String tradeNo;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 微信id
     */
    private String wxOpenId;

    /**
     * 收款用户姓名
     * 如果check_name设置为FORCE_CHECK，则必填用户真实姓名
     * 如需电子回单，需要传入收款用户姓名
     */
    private String reUserName;

    /**
     * 校验用户姓名选项
     * NO_CHECK：不校验真实姓名
     * FORCE_CHECK：强校验真实姓名
     */
    private String checkName;

    /**
     * 金额，单位分
     */
    @Range(min = 600, max = 6000, message = "提现金额最低6.00元或累计超过60.00元")
    @NotNull(message = "amount金额不能为空")
    private Integer amount;

    /**
     * 订单说明
     */
    private String desc;

    /**
     * 该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。
     */
    private String ip;

    /**
     * 微信支付分配的终端设备号
     */
    private String deviceInfo;

}
