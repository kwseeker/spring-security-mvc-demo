package top.kwseeker.security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.kwseeker.security.demo.util.filter.TimeFilter;
import top.kwseeker.security.demo.util.interceptor.TimeInterceptor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Configuration      //相当于 spring-mvc 项目中 .xml 的configuration配置
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 过滤器可以指定对哪些URL请求起作用
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");     //针对那些URL起作用
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }

    /**
     * 拦截器对所有请求起作用
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
