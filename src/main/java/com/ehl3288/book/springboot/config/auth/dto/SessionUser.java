package com.ehl3288.book.springboot.config.auth.dto;

import com.ehl3288.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    //인증된 사용자 정보만 필요함
    public SessionUser(User user) {
        this.name= user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
