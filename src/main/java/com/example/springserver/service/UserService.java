package com.example.springserver.service;

import com.example.springserver.dto.UserDTO;
import com.example.springserver.model.User;
import com.example.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public UserDTO mapUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        return userDTO;
    }

    public User mapUserDTOToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        return user;
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User user : users){
            userDTOS.add(mapUserToUserDTO(user));
        }
        return userDTOS;
    }

    public UserDTO getUser(int id){
        User user = userRepository.findOne(id);
        return mapUserToUserDTO(user);
    }

    public UserDTO addUser(UserDTO userDTO){
        User user = mapUserDTOToUser(userDTO);
        User UserNew = userRepository.save(user);
        return mapUserToUserDTO(UserNew);
    }

    public UserDTO editUser(UserDTO userDTO){
        User user = userRepository.findOne(userDTO.getId());
        user.setName(userDTO.getName());
        User UserNew = userRepository.save(user);
        return mapUserToUserDTO(UserNew);
    }

    public UserDTO delete(UserDTO userDTO){
        User user = userRepository.findOne(userDTO.getId());
        userRepository.delete(user);
        return userDTO;
    }

}
