package com.web.filter;


import com.web.util.RedisUtils;
import com.web.util.UrlStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.filter
 * @ClassName: RoleFilter
 * @Author: gwl
 * @Description:
 * @Date: 2020/8/3 11:27
 * @Version: 1.0
 */

@Order(1)
@WebFilter(filterName = "RoleFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "URL",value = "http://localhost:8868")})
@Slf4j
public class RoleFilter implements Filter {

    private RedisUtils redisUtils;

    private boolean isCross = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        redisUtils =  ctx.getBean(RedisUtils.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestIP = IPdistinguish.getRequestIP(req);
        //app端
        if ("/app".equals(UrlStringUtil.AppUrlformatting(req.getRequestURI()))){
            //校验app用户是否登陆过
            if("/app/checklogin".equals(req.getRequestURI())){
                req.getRequestDispatcher("/checklogin").forward(servletRequest,servletResponse);
            }else if (URI_Release.checkURI(UrlStringUtil.AppUrlVisit(req.getRequestURI()))){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                log.info("识别号---->"+req.getParameter("staff_identification_code"));
                log.info("识别号--补位---->"+req.getParameter("staff_identification_code").replace(" ", "+"));
                String staff_identification_code=req.getParameter("staff_identification_code").replace(" ", "+");
                if(redisUtils.get(staff_identification_code)!=null){
                    String staff = redisUtils.get(staff_identification_code).toString();
                    String str = UrlStringUtil.AppUrlVisit(req.getRequestURI());
                    log.info("进行App权限校验");
                    if (staff.indexOf(str)>0){
                        log.info("app权限校验通过!");
                        filterChain.doFilter(servletRequest,servletResponse);
                    }else {
                        req.getRequestDispatcher("/AppInsufficientAuthority").forward(servletRequest,servletResponse);
                    }
                }else {
                    req.getRequestDispatcher("/AppNoAuthority").forward(servletRequest,servletResponse);
                }
            }
        }
        //小程序端
        else if("/applets".equals(UrlStringUtil.AppUrlformatting(req.getRequestURI()))){
             log.info("小程序------");
            filterChain.doFilter(servletRequest,servletResponse);
        }
        //web端
        else{
            if (req.getRequestURI().equals("/login")){
                log.info("请求用户名--->"+req.getParameter("user_loginname"));
                log.info("请求密码--->"+req.getParameter("user_password"));
                if (redisUtils.get(req.getParameter("user_loginname"))==null){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else {
                    if(req.getParameter("repeat")!=null && !"".equals(req.getParameter("repeat"))){
                        redisUtils.del((String) redisUtils.get(req.getParameter("user_loginname")));
                        filterChain.doFilter(servletRequest,servletResponse);
                    }else {
                        //System.out.println("requestIP--->"+requestIP+"\t\t"+"loginnameIP---->"+redisUtils.get(req.getParameter("user_loginname")));
                       /* String user_loginname = req.getParameter("user_loginname");
                        String requestIP2 =  requestIP+"_"+user_loginname;*/
                        if(requestIP.equals(redisUtils.get(req.getParameter("user_loginname")))){
                            filterChain.doFilter(servletRequest,servletResponse);
                        }else {
                            req.getRequestDispatcher("/UserLoginon").forward(servletRequest, servletResponse);
                        }
                    }
                }
            }else {
                if (URI_Release.checkURI(UrlStringUtil.AppUrlVisit(req.getRequestURI()))){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else {
                    /*String user_loginname = req.getParameter("user_loginname");
                String requestIP2 =  requestIP+"_"+user_loginname;
                System.out.println(requestIP2);*/
                    Map<Object, Object> userdata = redisUtils.hmget(requestIP);
                    if (userdata.get("user")!=null){
                        log.info("进行web权限校验>>>>>>");
                        String str = userdata.get("user").toString();
                        String urlformatting = UrlStringUtil.Urlformatting(req.getRequestURI());
                        System.out.println("截取后的url---->"+urlformatting);
                        if (str.indexOf(urlformatting)>0){
                            log.info("web权限校验通过!");
                            filterChain.doFilter(servletRequest,servletResponse);
                        }else {
                            req.getRequestDispatcher("/InsufficientAuthority").forward(servletRequest,servletResponse);
                        }
                    }else {
                        log.error("web缓存失效>>>");
                        req.getRequestDispatcher("/NoAuthority").forward(servletRequest,servletResponse);
                    }
                }
            }
        }






    }


}
