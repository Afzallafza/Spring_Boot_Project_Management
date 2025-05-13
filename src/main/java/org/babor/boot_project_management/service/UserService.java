package org.babor.boot_project_management.service;


import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.user.UserRequestDto;
import org.babor.boot_project_management.dto.user.UserResponseDto;
import org.babor.boot_project_management.entity.User;
import org.babor.boot_project_management.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User findByUsernameCustom(String username) {
        return userRepository.findByUsernameCustom(username);
    }

    @Transactional
    public UserResponseDto findDtoById(int userId) {
        return modelMapper.map(userRepository.findByIdCustom(userId), UserResponseDto.class);
    }

    public User findByIdCustom(int id) {
        return userRepository.findByIdCustom(id);
    }

    @Transactional
    public UserResponseDto create(UserRequestDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(modelMapper.map(user, User.class));
        return modelMapper.map(userRepository.findByUsernameCustom(user.getUsername()), UserResponseDto.class);
    }

    @Transactional
    public List<UserResponseDto> findAllByProjectId(int projectId) {
        return userRepository.findAllByProjectId(projectId);
    }

}
