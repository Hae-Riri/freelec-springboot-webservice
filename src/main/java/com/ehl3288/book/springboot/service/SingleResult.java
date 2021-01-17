package com.ehl3288.book.springboot.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult <T> extends CommonResult{
    private T data;
}
