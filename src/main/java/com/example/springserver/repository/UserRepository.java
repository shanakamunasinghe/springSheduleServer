package com.example.springserver.repository;

import com.example.springserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByName(String userName);
    public User findByEmail(String email);


}
