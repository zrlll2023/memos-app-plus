package com.memos.memosappplus.common;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
现在如果注册时用户名已存在，后端会抛出 RuntimeException("用户名已存在")，但返回给前端的不是我们的 Result 格式，而是 Spring 默认的错误页面，很难看。
我们需要一个全局异常处理器，把所有异常统一转成 Result 格式返回。
 */

// 全局异常处理器
@RestControllerAdvice
/*
    @RestControllerAdvice = @ControllerAdvice + @ResponseBody
    @ControllerAdvice 的意思是「Controller 的增强器」——它能拦截所有 Controller 抛出的异常，统一处理。
    就像一个兜底网：
    任何 Controller 里抛出了异常，不管是哪个 Controller，都会被这里捕获，然后统一返回 Result 格式。
    这其实是 AOP 思想的体现——不修改原有代码，在外面加一层统一处理。
*/
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        return Result.error(message);
    }
}
