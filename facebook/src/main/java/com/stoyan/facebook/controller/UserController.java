package com.stoyan.facebook.controller;

import com.stoyan.facebook.model.DTOs.LoginDTO;
import com.stoyan.facebook.model.DTOs.RegisterDTO;
import com.stoyan.facebook.model.DTOs.UserWithoutPassDTO;
import com.stoyan.facebook.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends AbstractController{
    @Autowired
    private UserService userService;
    @PostMapping("/users")
    public UserWithoutPassDTO register(@RequestBody RegisterDTO dto){
        return userService.register(dto );
    }
    @PostMapping("/users/auth")
    public UserWithoutPassDTO login(@RequestBody LoginDTO dto, HttpSession s){
        UserWithoutPassDTO respDto = userService.login(dto);
        s.setAttribute("LOGGED", true);
        s.setAttribute("LOGGED_ID", respDto.getId());
        return respDto;
    }
    @GetMapping("/users/{id}")
    public UserWithoutPassDTO getById(@PathVariable int id){
        return userService.getById(id);
    }
    @GetMapping("/users")
    public List<UserWithoutPassDTO> getAll(){
        return userService.getAll();
    }
}
