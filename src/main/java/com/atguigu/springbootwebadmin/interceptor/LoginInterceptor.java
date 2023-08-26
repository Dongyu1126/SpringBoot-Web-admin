package com.atguigu.springbootwebadmin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/*
* 拦截器：登录检查
* 1、配置好拦截器要拦截哪些请求
* 2、把这些配置放在容器中
* */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
/*
* 目标方法执行之前
 * @param request
 * @param response
 * @param handler
 * @return
 * @throws Exception
* */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //打印拦截的路径
        String requestURI = request.getRequestURI();
        log.info("拦截请求的路径是{}",requestURI);

        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){//只要有登录信息就放行
            return true;
        }else {
            //拦截住就是未登录，未登录让其跳转到登录页面
            request.setAttribute("msg","请先登录");//改成request里面取msg值，因为thymeleaf里面th:text只能取request值
//            session.setAttribute("msg","请先登录");
//            response.sendRedirect("/");//重定向
            request.getRequestDispatcher("/").forward(request,response);
            return false; //没有信息就拦截住
        }

    }
/*
* 目标方法执行以后
* */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
/*
* 页面渲染之后
* */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
