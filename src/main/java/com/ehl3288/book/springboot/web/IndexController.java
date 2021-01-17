package com.ehl3288.book.springboot.web;

import com.ehl3288.book.springboot.service.ResponseService;
import com.ehl3288.book.springboot.service.SingleResult;
import com.ehl3288.book.springboot.service.posts.PostsService;
import com.ehl3288.book.springboot.web.dto.PostsResponseDto;
import com.ehl3288.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final ResponseService responseService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
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
