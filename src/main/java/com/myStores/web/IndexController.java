package com.myStores.web;

import com.myStores.config.auth.LoginUser;
import com.myStores.config.auth.dto.SessionUser;
import com.myStores.service.PostsService;
import com.myStores.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){ //@LoginUser SessionUser user
//        model.addAttribute("posts",postsService.findAllDesc());
//        if(user != null){
//            model.addAttribute("loginName",user.getName());
//        }
        return "form/index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
