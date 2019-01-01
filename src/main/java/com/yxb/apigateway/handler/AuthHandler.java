package com.yxb.apigateway.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
public interface AuthHandler {

    boolean auth(HttpServletRequest request, HttpServletResponse response);
}
