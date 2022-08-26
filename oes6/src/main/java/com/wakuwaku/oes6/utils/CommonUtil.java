package com.wakuwaku.oes6.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 帮助类
 *
 * @author kou
 */
public class CommonUtil {

    /**
     * 获取 ip 地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            if (ips.length > 1) {
                ip = ips[0];
            }
        }
        return ip;
    }
}
