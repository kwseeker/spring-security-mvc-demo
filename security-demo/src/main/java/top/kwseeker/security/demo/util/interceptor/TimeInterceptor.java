package top.kwseeker.security.demo.util.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("TimeInterceptor preHandle");
        System.out.println( ((HandlerMethod)handler).getBean().getClass().getName() + "\n"
            + ((HandlerMethod)handler).getMethod().getName());

        request.setAttribute("startTime", new Date().getTime());
        return true;    //true调用handler方法， false不调用handler方法, handler方法值处理请求调用的controller方法
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView var4)
            throws Exception {
        System.out.println("TimeInterceptor postHandle");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("TimeInterceptor 请求返回成功耗时：" + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        System.out.println("TimeInterceptor afterCompletion");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("TimeInterceptor 请求结束耗时：" + (new Date().getTime() - start));
        System.out.println("exception is " + exception);
    }
}
