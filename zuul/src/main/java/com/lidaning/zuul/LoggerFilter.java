package com.lidaning.zuul;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class LoggerFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 过滤器的类型
     *      pre, post, route, error
     */
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    /**
     * 默认为false, 不开启
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * 具体的业务逻辑
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext request = RequestContext.getCurrentContext();
        HttpServletRequest httpRequest = request.getRequest();
        logger.info("logerFilter from zuul: method:{}, url={}", httpRequest.getMethod(), httpRequest.getRequestURL().toString());
        return null;
    }
}
