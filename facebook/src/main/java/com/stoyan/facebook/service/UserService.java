package com.stoyan.facebook.service;

import com.stoyan.facebook.model.DTOs.LoginDTO;
import com.stoyan.facebook.model.DTOs.RegisterDTO;
import com.stoyan.facebook.model.DTOs.UserWithoutPassDTO;
import com.stoyan.facebook.model.entities.User;
import com.stoyan.facebook.model.exceptions.BadRequestException;
import com.stoyan.facebook.model.exceptions.NotFoundException;
import com.stoyan.facebook.model.exceptions.UnauthorizedException;
import com.stoyan.facebook.model.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    public UserWithoutPassDTO register(RegisterDTO dto) {
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new BadRequestException("Passwords mismatch!");
        }
//            if(!dto.getPassword().matches()){
//              throw new BadRequestException("Weak password!");
//        }
        if (userRepository.existsByEmail(dto.getEmail())){
            throw new BadRequestException("Email already exists!");
        }
        User u = mapper.map(dto, User.class);
        u.setPassword(encoder.encode(u.getPassword()));
        userRepository.save(u);
        return mapper.map(u, UserWithoutPassDTO.class);
    }

    public UserWithoutPassDTO login(LoginDTO dto) {
        Optional<User> u = userRepository.getByEmail(dto.getEmail());
        if(!u.isPresent()){
            throw new UnauthorizedException("Wrong credentials!");
        }
        if(!encoder.matches(dto.getPassword(), u.get().getPassword())){
            throw new UnauthorizedException("Wrong credentials!");
        }
        return mapper.map(u, UserWithoutPassDTO.class);
    }

    public UserWithoutPassDTO getById(int id) {
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            return mapper.map(u.get(), UserWithoutPassDTO.class);
        }
        throw new NotFoundException("User not found!");
    }

    public List<UserWithoutPassDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> mapper.map(u, UserWithoutPassDTO.class))
                .collect(Collectors.toList());
    }
}
