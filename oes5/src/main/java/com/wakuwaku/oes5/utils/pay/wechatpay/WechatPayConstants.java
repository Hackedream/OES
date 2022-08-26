package com.wakuwaku.oes5.utils.pay.wechatpay;

import org.apache.http.client.HttpClient;

/**
 * 微信支付常量
 *
 * @author kou
 */
public class WechatPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    public static final String DOMAIN_API = "https://api.mch.weixin.qq.com";
    public static final String DOMAIN_API2 = "https://api2.mch.weixin.qq.com";
    public static final String DOMAIN_APIHK = "https://apihk.mch.weixin.qq.com";
    public static final String DOMAIN_APIUS = "https://apius.mch.weixin.qq.com";

    /**
     * 交易类型 JSAPI支付
     */
    public static final String TRADE_TYPE_JSAPI = "JSAPI";

    /**
     * 交易类型 Native支付
     */
    public static final String TRADE_TYPE_NATIVE = "NATIVE";

    /**
     * 交易类型 APP支付
     */
    public static final String TRADE_TYPE_APP = "APP";


    public static final String FAIL = "FAIL";
    public static final String SUCCESS = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";

    public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
    public static final String USER_AGENT = WXPAYSDK_VERSION +
            " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") +
            ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();

    /**
     * 企业支付
     */
    public static final String TRANSFERS_URL_SUFFIX = "/mmpaymkttransfers/promotion/transfers";

    public static final String MICROPAY_URL_SUFFIX = "/pay/micropay";

    /**
     * 统一下单
     */
    public static final String UNIFIEDORDER_URL_SUFFIX = "/pay/unifiedorder";

    /**
     * 查询订单
     */
    public static final String ORDERQUERY_URL_SUFFIX = "/pay/orderquery";


    public static final String REVERSE_URL_SUFFIX = "/secapi/pay/reverse";
    public static final String CLOSEORDER_URL_SUFFIX = "/pay/closeorder";
    public static final String REFUND_URL_SUFFIX = "/secapi/pay/refund";
    public static final String REFUNDQUERY_URL_SUFFIX = "/pay/refundquery";
    public static final String DOWNLOADBILL_URL_SUFFIX = "/pay/downloadbill";
    public static final String REPORT_URL_SUFFIX = "/payitil/report";
    public static final String SHORTURL_URL_SUFFIX = "/tools/shorturl";
    public static final String AUTHCODETOOPENID_URL_SUFFIX = "/tools/authcodetoopenid";

    /**
     * sandbox
     */
    public static final String SANDBOX_MICROPAY_URL_SUFFIX = "/sandboxnew/pay/micropay";
    public static final String SANDBOX_UNIFIEDORDER_URL_SUFFIX = "/sandboxnew/pay/unifiedorder";
    public static final String SANDBOX_ORDERQUERY_URL_SUFFIX = "/sandboxnew/pay/orderquery";
    public static final String SANDBOX_REVERSE_URL_SUFFIX = "/sandboxnew/secapi/pay/reverse";
    public static final String SANDBOX_CLOSEORDER_URL_SUFFIX = "/sandboxnew/pay/closeorder";
    public static final String SANDBOX_REFUND_URL_SUFFIX = "/sandboxnew/secapi/pay/refund";
    public static final String SANDBOX_REFUNDQUERY_URL_SUFFIX = "/sandboxnew/pay/refundquery";
    public static final String SANDBOX_DOWNLOADBILL_URL_SUFFIX = "/sandboxnew/pay/downloadbill";
    public static final String SANDBOX_REPORT_URL_SUFFIX = "/sandboxnew/payitil/report";
    public static final String SANDBOX_SHORTURL_URL_SUFFIX = "/sandboxnew/tools/shorturl";
    public static final String SANDBOX_AUTHCODETOOPENID_URL_SUFFIX = "/sandboxnew/tools/authcodetoopenid";

    /**
     * 微信native支付回调地址
     */
    public static final String NOTIFY_URL = "/wallet/pay/notify/wxpay";

}