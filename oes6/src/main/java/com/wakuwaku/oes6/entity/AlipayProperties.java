package com.wakuwaku.oes6.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置
 *
 * @author kou
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "pay.alipay")
@Component
public class AlipayProperties {

    /**
     * 支付宝网关
     */
    private String gatewayUrl;

    /**
     * 支付宝 app-id
     */
    private String appId;

    /**
     * 支付宝 私钥
     */
    private String privateKey;

    /**
     * 支付宝公钥
     */
    private String publickey;

    /**
     * 支付宝 签名类型
     */
    private String signType;

    /**
     * 格式
     */
    private String format;

    /**
     * 格式
     */
    private String charset;

    /**
     * 应用公钥证书路径
     */
    private String appCertPath;

    /**
     * 支付宝公钥证书文件路径
     */
    private String alipayCertPath;

    /**
     * 支付宝CA根证书文件路径
     */
    private String alipayRootCertPath;

    /**
     * 收款码时效时间
     */
    private String qrCodeTimeoutExpress;

    /**
     * 回调地址
     */
    private String returnUrl;

    /**
     * 异步地址
     */
    private String notifyUrl;

    /**
     * 最大查询次数
     */
    private static int maxQueryRetry = 5;
    /**
     * 查询间隔（毫秒）
     */
    private static long queryDuration = 5000;
    /**
     * 最大撤销次数
     */
    private static int maxCancelRetry = 3;
    /**
     * 撤销间隔（毫秒）
     */
    private static long cancelDuration = 3000;

    /**
     * PostContruct是spring框架的注解，在spring容器初始化的时候执行该方法。
     */
    // @PostConstruct
    public void init() {
        log.info(description());
    }

    public String description() {
        StringBuilder sb = new StringBuilder("\n支付宝Configs\n{\n");
        sb.append("支付宝网关: ").append(gatewayUrl).append("\n");
        sb.append("appId: ").append(appId).append("\n");
        sb.append("商户RSA私钥: ").append("***").append("\n");
        sb.append("应用公钥证书: ").append(appCertPath).append("\n");
        sb.append("支付宝公钥证书: ").append(alipayCertPath).append("\n");
        sb.append("支付宝根证书: ").append(alipayRootCertPath).append("\n");
        sb.append("支付宝RSA公钥: ").append("***").append("\n");
        sb.append("签名类型: ").append(signType).append("\n");
        sb.append("查询重试次数: ").append(maxQueryRetry).append("\n");
        sb.append("查询间隔(毫秒): ").append(queryDuration).append("\n");
        sb.append("撤销尝试次数: ").append(maxCancelRetry).append("\n");
        sb.append("撤销重试间隔(毫秒): ").append(cancelDuration).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
