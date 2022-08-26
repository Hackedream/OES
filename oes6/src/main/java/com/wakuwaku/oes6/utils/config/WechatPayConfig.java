package com.wakuwaku.oes6.utils.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置
 *
 * @author kou
 */
@Data
@ConfigurationProperties(prefix = "pay.wxpay")
@Component
public class WechatPayConfig {

    /**
     * 应用id，appId
     */
    private String appId;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 秘钥
     */
    private String appKey;

    /**
     * 证书路径
     */
    private String certPath;

    /**
     * key路径
     */
    private String keyPath;

    /**
     * 最小提现额度
     */
    private Integer minLimit;

    /**
     * 最大提现额度
     */
    private Integer maxLimit;

}
