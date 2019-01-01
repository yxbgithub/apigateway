package com.yxb.apigateway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * Cookie 工具类
 *
 */
public final class CookieUtils {

    public static void setCookie(HttpServletResponse response,String cookieName,String cookieValue,int maxAge) {
        try {
            cookieValue=URLEncoder.encode(cookieValue,"UTF-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // cookie的有效期
        //long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
        // 将要被保存的完整的Cookie值

        // 开始保存Cookie
        Cookie cookie = new Cookie(cookieName, cookieValue);
        // 设置生命周期
        cookie.setMaxAge(maxAge);
        // cookie有效路径是网站根目录
        cookie.setPath("/");
        // 向客户端写入
        response.addCookie(cookie);
    }
    // 用户注销时,清除Cookie,在需要时可随时调用------------------------------------------------------------
    public static void clearCookie(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 根据cookieName取cookieValue
    public static String getCookieValue(HttpServletRequest request,String cookieName) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookieName.equals(cookies[i].getName())) {
                    cookieValue = cookies[i].getValue();
                    try {
                        cookieValue=URLDecoder.decode(cookieValue,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return cookieValue;
    }

}
