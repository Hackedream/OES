package com.wakuwaku.oes6.utils.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付配置
 *
 * @author kou
 */
@Data
@ConfigurationProperties(prefix = "pay.config")
@Component
public class PayConfig {

    /**
     * 最小提现额度
     */
    private Integer minLimit;

    /**
     * 最小提现金额
     */
    private String minAmountLimit;

    /**
     * 最大提现额度
     */
    private Integer maxLimit;

    /**
     * 最大提现金额
     */
    private String maxAmountLimit;

    /**
     * 最大提现次数限制
     */
    private Integer maxTimes;

    /**
     * 提现整倍数限制
     */
    private Integer multiple;

    /**
     * 提现税率，提现后金额 = 提现金额 * 税率
     */
    private String rate;

}
