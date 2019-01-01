package com.yxb.apigateway.handler.impl;

import com.yxb.apigateway.handler.AuthHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
public class OrderAuthHandler implements AuthHandler {
    @Override
    public boolean auth(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }
}
