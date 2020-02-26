package com.example.springserver.service;

import com.example.springserver.dto.RoleDTO;
import com.example.springserver.model.Role;
import com.example.springserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    public RoleRepository roleRepository;

    public RoleDTO mapRoleToRoleDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole_id(role.getRole_id());
        roleDTO.setName(role.getName());
        roleDTO.setCreated(role.getCreated());
        if(role.getModified() != null){
            roleDTO.setModified(role.getModified());
        }
        return roleDTO;
    }

    public Role mapRoleDTOToRole(RoleDTO roleDTO){
        Role role = new Role();
        role.setRole_id(roleDTO.getRole_id());
        role.setName(roleDTO.getName());
        return role;
    }

    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> roleDTOS = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for(Role role: roles){
            roleDTOS.add(mapRoleToRoleDTO(role));
        }
        return roleDTOS;
    }

    public RoleDTO getRole(int id){
        Role role = roleRepository.getOne(id);
        return mapRoleToRoleDTO(role);
    }

    public RoleDTO addRole(RoleDTO roleDTO){
        Role role = mapRoleDTOToRole(roleDTO);
        role.setCreated(new Date());
        Role roleNew = roleRepository.save(role);
        return mapRoleToRoleDTO(roleNew);
    }

    public RoleDTO editRole(RoleDTO roleDTO){
        Role role = roleRepository.getOne(roleDTO.getRole_id());
        role.setName(roleDTO.getName());
        role.setModified(new Date());
        Role roleNew = roleRepository.save(role);
        return mapRoleToRoleDTO(roleNew);
    }

    public RoleDTO delete(RoleDTO roleDTO){
        Role role = roleRepository.getOne(roleDTO.getRole_id());
        if(role != null){
            roleRepository.delete(role);
        }
        return roleDTO;
    }
}
