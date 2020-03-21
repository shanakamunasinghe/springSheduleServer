package com.example.springserver.service;

import com.example.springserver.dto.RoleDTO;
import com.example.springserver.model.Role;
import com.example.springserver.repository.RoleRepository;
import com.example.springserver.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    public List<RoleDTO> findByUserId(int id){
        List<RoleDTO> roleDTOS = new ArrayList<>();
        List<Integer> roleIds =  userRolesRepository.findRolesByUserId(id);
        if(roleIds == null){
            return roleDTOS;
        }
        List<Role> roles = roleRepository.findAll(roleIds);
        for(Role role : roles){
            roleDTOS.add(roleService.mapRoleToRoleDTO(role));
        }
        return roleDTOS;
    }
}
