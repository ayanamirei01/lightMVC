package com.pan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:33
 * @Version 1.0
 */
@ToString
@Getter
@Setter
public class Result<T> {

    private boolean success;
    private String error;
    private T data;

    public Result(T data,boolean success) {
        this.data = data;
        this.success = success;
    }
    public Result(boolean success,String error) {
        this.error = error;
        this.success = success;
    }

    public Result(){}
}
