package com.yxb.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yxb.apigateway.constant.CookieConstant;
import com.yxb.apigateway.constant.RedisConstant;
import com.yxb.apigateway.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 鉴权
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
/*@Component*/
public class AuthFilter extends ZuulFilter {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        javax.servlet.http.HttpServletRequest request = context.getRequest();
        /**
         * /order/create只能卖家访问
         * /order/finish只能卖家访问
         * /product/list都能访问
         */
        //url前面那个order是serviceId
        if ("/order/order/create".equals(request.getRequestURL())) {
            String value = CookieUtils.getCookieValue(request, CookieConstant.OPENID);
            if (StringUtils.isEmpty(value)) {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            }
        } else if ("/order/order/finish".equals(request.getRequestURL())) {
            String token = CookieUtils.getCookieValue(request, CookieConstant.TOKEN);
            if (StringUtils.isNotEmpty(token)) {
                String value = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, token));
                if (StringUtils.isNotEmpty(value))
                    return null;
            }
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null;
    }
}
