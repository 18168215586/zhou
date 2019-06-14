package com.lgz.cras.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*转换作用域*/
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        /*获取项目根路径*/
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath()
                + "/";
        /*根路径存入作用域*/
        request.setAttribute("basePath", basePath);

        /*请求过滤白名单*/
        String[] paths=new String[]{"/login.do","/exit.do"};
        /*获取当前请求地址*/
        String url=request.getServletPath();

        boolean isGo=false;
        for (String s:paths){
            if (s.equals(url)){
                isGo=true;
                break;
            }
        }
        if (isGo){/*白名单地址直接放行*/
            /*放行请求*/
            filterChain.doFilter(request,response);
        }else {
            /*不在白名单的地址判断session*/
            HttpSession session=request.getSession();
            /*获取登录用户名*/
            String uname=MyUtils.getLoginName(session);
            /*登录用户名不为空表示还在登录状态*/
            if (StringUtils.isNotEmpty(uname)){
                /*放行请求*/
                filterChain.doFilter(request,response);
            }else {
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out=response.getWriter();
                out.print("<html><head><script>alert('登录失效,请重新登录！');top.location.replace('"+basePath+"');</script></head><body></body></html>");


            }
        }
    }
}
