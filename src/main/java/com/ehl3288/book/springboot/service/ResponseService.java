package com.ehl3288.book.springboot.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Getter
    @AllArgsConstructor
    public enum CommonResponse{
        SUCCESS(0,"성공하였습니다."),
        FAIL(-1, "실패하였습니다.");
        int code;
        String msg;
    }

    private MessageSource messageSource;

    @Builder
    public ResponseService(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public <T> SingleResult<T> getSingeResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
