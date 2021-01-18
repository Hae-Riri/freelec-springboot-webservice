package com.ehl3288.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //이 어노테이션이 생성될 수 있는 위치를 정함. @LoginUser는 인자로서만 생성 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //이 파일을 어노테이션 클래스로 지정함

}
