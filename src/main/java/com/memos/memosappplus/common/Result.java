package com.memos.memosappplus.common;

import lombok.Data;

@Data
/*
* 这些 set 方法是属于 Result 类自己的——result.setCode(200) 是在给 Result 对象的 code 字段赋值。
* 所以 Result 类也需要有自己的 setter 方法，
* 不加 @Data 的话这些 setCode()、setMessage()、setData() 方法就不存在，
* 代码会报错。
* */
public class Result<T> {
    private Integer code;    // 状态码，比如 200、400
    private String message;  // 提示信息
    private T data;          // 返回的数据，类型不固定所以用泛型 T

    // 成功
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        // 你需要先创建一个 Result 对象，然后把字段 set 进去，最后 return 出去
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    // 失败
    public static <T> Result<T> error(String message /* 调用方传进来的错误信息就存在这个变量里，直接用它就行 */) {
        Result<T> result = new Result<>();
        result.setCode(400);
        result.setMessage(message);
        //error 方法接收了一个 message 参数，比如调用方传来 "用户名已存在"，你应该把这个参数传进去
        result.setData(null);
        return result;
    }

}
