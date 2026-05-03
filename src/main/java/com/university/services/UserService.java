package com.university.services;

import com.university.Repository.UserRepository;
import com.university.dtos.response.UserDto;
import com.university.entity.User;
import com.university.exceptions.ResourceNotFoundException;
import com.university.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }
}
