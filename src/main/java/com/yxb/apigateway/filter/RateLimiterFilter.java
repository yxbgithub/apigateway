package com.yxb.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 限流
 * Author by yanxiaobo
 * Date on 2018/12/16$.
 */
/*@Component*/
public class RateLimiterFilter extends ZuulFilter {
    //每秒放置100个令牌
    public static final  RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //限流的优先级最高。所以这里比现有的最高的优先级-1
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!RATE_LIMITER.tryAcquire()) {//如果没有拿到令牌
            throw new RuntimeException();
        }
        return null;
    }
}
