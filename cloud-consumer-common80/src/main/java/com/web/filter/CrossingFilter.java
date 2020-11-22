package com.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.filter
 * @ClassName: CrossingFilter
 * @Author: gwl
 * @Description:
 * @Date: 2020/8/18 10:37
 * @Version: 1.0
 */

@Order(1)
@WebFilter(filterName = "CrossingFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "URL",value = "http://localhost:8868")})
@Slf4j
public class CrossingFilter implements Filter {
    private boolean isCross = false;

    @Override
    public void destroy() {
        isCross = false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (isCross) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            log.info("拦截请求: " + httpServletRequest.getServletPath());
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            // httpServletResponse.setHeader("Access-Control-Allow-Methods", "*"); // 表示所有请求都有效
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "0");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("XDomainRequestAllowed", "1");
        }
        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String isCrossStr = "true";
        isCross = isCrossStr.equals("true") ? true : false;
        log.info("跨域开启状态：" + isCrossStr);
    }
}
