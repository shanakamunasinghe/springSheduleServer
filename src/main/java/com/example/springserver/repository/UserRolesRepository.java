package com.example.springserver.repository;

import com.example.springserver.model.Role;
import com.example.springserver.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles,Integer> {
    @Query(value = "select role_id from public.user_roles where user_id =:id", nativeQuery = true)
    public List<Integer> findRolesByUserId(int id);
}
