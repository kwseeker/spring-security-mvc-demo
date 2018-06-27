## Spring Security

#### Spring Boot 基础补充

+ **请求异步处理的三种方法**
    https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/web.html#mvc-ann-async
    
    - **Callable<>**  
        Callable异步请求的处理流程：
        1) 控制器先返回一个Callable对象
        2) Spring MVC开始进行异步处理，并把该Callable对象提交给另一个独立线程的执行器TaskExecutor处理
        3) DispatcherServlet和所有过滤器都退出Servlet容器线程，但此时方法的响应对象仍未返回
        4) Callable对象最终产生一个返回结果，此时Spring MVC会重新把请求分派回Servlet容器，恢复处理
        5) DispatcherServlet再次被调用，恢复对Callable异步处理所返回结果的处理
        
    - **DeferredResult<>**   
        https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/async/DeferredResult.html
        DeferredResult异步请求的处理流程：
        1) 控制器先返回一个DeferredResult对象，并把它存取在内存（队列或列表等）中以便存取
        2) Spring MVC开始进行异步处理
        3) DispatcherServlet和所有过滤器都退出Servlet容器线程，但此时方法的响应对象仍未返回
        4) 由处理该请求的线程对 DeferredResult进行设值，然后Spring MVC会重新把请求分派回Servlet容器，恢复处理
        5) DispatcherServlet再次被调用，恢复对该异步返回结果的处理
        
    - **@EnableAsync @Async**
    
    前两者可以返回最终处理的结果返回给前端；最后一种方式使用最方便，但是无法返回后台处理结果。
    
+ **ApplicationListener<>**  
    https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#context-functionality-events
    
    - 内置事件
    ContextRefreshedEvent
    ContextStartedEvent
    ContextStoppedEvent
    ContextClosedEvent
    RequestHandledEvent
    
    - 自定义事件
    - 基于注解的事件处理
    - 异步监听器 @Async
    - 有先后顺序的监听器 @Order
    - 通用事件
    
