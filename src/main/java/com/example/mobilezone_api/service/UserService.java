package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.UserDTO;
import com.example.mobilezone_api.mapper.UserMapper;
import com.example.mobilezone_api.model.User;
import com.example.mobilezone_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id -" + id));
        return user;
    }

    @Transactional
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email -" + email));
        return user;
    }

    @Transactional
    public User save(UserDTO userDTO) {
        User user = userMapper.mapDTOToUser(userDTO);
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
