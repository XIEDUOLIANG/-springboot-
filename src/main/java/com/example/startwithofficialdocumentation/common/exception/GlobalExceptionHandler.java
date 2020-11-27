package com.example.startwithofficialdocumentation.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author XieDuoLiang
 * @date 2020/11/26 下午2:08
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public String exceptionHandler(HttpServletRequest request,Exception e) {
        log.error("请求发生异常的地址：{},错误描述：{}",request.getRequestURL(),e.getMessage());
        return "error";
    }
}
