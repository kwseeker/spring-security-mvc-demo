package top.kwseeker.security.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.kwseeker.security.demo.exception.UserNotExistException;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Boot 自定义异常处理器
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * UserNotExistException异常处理器
     * @param ex
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)  //指定处理哪个异常，这个处理函数会覆盖默认的处理函数
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //返回给前端的状态码
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return  result;
    }



}
