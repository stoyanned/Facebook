package com.stoyan.facebook.controller;

import com.stoyan.facebook.model.DTOs.CreatePostDTO;
import com.stoyan.facebook.model.DTOs.PostInfoDTO;
import com.stoyan.facebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController extends AbstractController{
    @Autowired
    private PostService postService;
    @PostMapping("/posts")
    public PostInfoDTO create(@RequestBody CreatePostDTO dto){
        return PostService.create(dto);
    }
}
