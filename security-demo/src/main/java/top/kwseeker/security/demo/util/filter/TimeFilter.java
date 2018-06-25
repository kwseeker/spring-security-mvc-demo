package top.kwseeker.security.demo.util.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 用于统计请求处理耗时
 */
//@Component  //注入, 如果不使用这种方式，还可以通过配置类将TimeFilter添加到系统 config/WebConfig.java
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig var1) throws ServletException {
        System.out.println("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("TimeFilter do");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("TimeFilter 请求处理耗时" + (new Date().getTime()-start));
        System.out.println("TimeFilter done");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter destory");
    }

}
