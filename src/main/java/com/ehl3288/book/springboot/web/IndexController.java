package com.ehl3288.book.springboot.web;

import com.ehl3288.book.springboot.config.auth.LoginUser;
import com.ehl3288.book.springboot.config.auth.dto.SessionUser;
import com.ehl3288.book.springboot.service.ResponseService;
import com.ehl3288.book.springboot.service.SingleResult;
import com.ehl3288.book.springboot.service.posts.PostsService;
import com.ehl3288.book.springboot.web.dto.PostsResponseDto;
import com.ehl3288.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    private final ResponseService responseService;

    //@LoginUser를 통해서 httpSession으로 가져오던 세션 정보 값을 개선함
    //@LoginUser만 사용하면 언제든지 세션 정보를 가져올 수 있게 되었음
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하게 구성했었음. 따라서 로그인 성공 시
        // httpSession.getAttribute()에서 값을 가져올 수 있음
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){ //세션에 저장된 값이 있을 때만 model에 userName 으로 등록할 수 있음
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    @GetMapping("/hospital")
    public ResponseEntity<SingleResult<PostsSaveRequestDto>> getResponse(){
        PostsSaveRequestDto response = PostsSaveRequestDto.builder()
                .title("hi hello")
                .author("haerim")
                .content("dmd")
                .build();
        SingleResult<PostsSaveRequestDto> result = responseService.getSingeResult(response);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
