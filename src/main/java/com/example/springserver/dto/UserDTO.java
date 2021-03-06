package com.example.springserver.dto;

import java.util.Date;
import java.util.List;

public class UserDTO {
    private int user_id;
    private String name;
    private String password;
    private String email;
    private Date created;
    private Date modified;
    private List<RoleDTO> rolesDTO;

    public UserDTO() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<RoleDTO> getRolesDTO() {
        return rolesDTO;
    }

    public void setRolesDTO(List<RoleDTO> rolesDTO) {
        this.rolesDTO = rolesDTO;
    }
}
