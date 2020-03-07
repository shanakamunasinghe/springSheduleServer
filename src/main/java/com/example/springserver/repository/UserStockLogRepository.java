package com.example.springserver.repository;

import com.example.springserver.model.UserStockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStockLogRepository extends JpaRepository<UserStockLog,Integer> {
    @Query(value = "SELECT * FROM public.users_stocks_log usl WHERE usl.user_id =:user_id",nativeQuery = true)
    public List<UserStockLog> findAllByUser(int user_id);
}
