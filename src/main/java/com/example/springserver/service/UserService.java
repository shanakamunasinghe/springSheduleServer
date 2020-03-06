package com.example.springserver.service;

import com.example.springserver.dto.RoleDTO;
import com.example.springserver.dto.UserDTO;
import com.example.springserver.model.Role;
import com.example.springserver.model.User;
import com.example.springserver.repository.RoleRepository;
import com.example.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;


    public UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreated(user.getCreated());
        if (user.getModified() != null) {
            userDTO.setModified(user.getModified());
        }
        if(user.getRoles() != null){
            List<Role> roles = user.getRoles();
            List<RoleDTO> roleDTOS = new ArrayList<>();
            for(Role role : roles){
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setRole_id(role.getRole_id());
                roleDTO.setName(role.getName());
                roleDTOS.add(roleDTO);
            }
            userDTO.setRolesDTO(roleDTOS);
        }

        return userDTO;
    }

    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUser_id(userDTO.getUser_id());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        if(userDTO.getRolesDTO() != null) {
            List<Role> roles = new ArrayList<>();
            List<RoleDTO> roleDTOS = userDTO.getRolesDTO();
            for(RoleDTO roleDTO : roleDTOS){
                Role role = roleRepository.getOne(roleDTO.getRole_id());
                roles.add(role);
            }
            user.setRoles(roles);
        }
        return user;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            userDTOS.add(mapUserToUserDTO(user));
        }
        return userDTOS;
    }

    public UserDTO getUser(int id) {
        User user = userRepository.getOne(id);
        return mapUserToUserDTO(user);
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = mapUserDTOToUser(userDTO);
        user.setCreated(new Date());
        User userNew = userRepository.save(user);
        return mapUserToUserDTO(userNew);
    }

    public UserDTO editUser(UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getUser_id());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setModified(new Date());
        User userNew = userRepository.save(user);
        return mapUserToUserDTO(userNew);
    }

    public UserDTO delete(UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getUser_id());
        if(user != null){
            userRepository.delete(user);
        }
        return userDTO;
    }

}
