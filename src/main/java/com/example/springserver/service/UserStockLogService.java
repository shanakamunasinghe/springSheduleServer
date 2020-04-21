package com.example.springserver.service;


import com.example.springserver.dto.UserStockLogDTO;
import com.example.springserver.model.User;
import com.example.springserver.model.UserStockLog;
import com.example.springserver.repository.StockRepository;
import com.example.springserver.repository.UserRepository;
import com.example.springserver.repository.UserStockLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStockLogService {
    @Autowired
    public UserService userService;

    @Autowired
    public StockService stockService;

    @Autowired
    public UserStockLogRepository userStockLogRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public StockRepository stockRepository;

    public UserStockLogDTO mapUserStockLogToUserStockLogDTO(UserStockLog UserStockLog){
        UserStockLogDTO userStockLogDTO = new UserStockLogDTO();
        userStockLogDTO.setUser_stock_id(UserStockLog.getUser_stock_id());
        userStockLogDTO.setAmount(UserStockLog.getAmount());
        userStockLogDTO.setShares(UserStockLog.getShares());
        userStockLogDTO.setCreated(UserStockLog.getCreated());
        userStockLogDTO.setUserDTO(userService.mapUserToUserDTO(UserStockLog.getUser()));
        userStockLogDTO.setStockDTO(stockService.mapStockToStockDTO(UserStockLog.getStock()));

        return userStockLogDTO;
    }


    public List<UserStockLogDTO> getAllUserLogs(int user_id){
        User user = userRepository.getOne(user_id);
        List<UserStockLogDTO> userStockLogDTOS = new ArrayList<>();
        List<UserStockLog> userStockLogs = new ArrayList<>();
        if(user != null){
            userStockLogs  = userStockLogRepository.findAllByUser(user_id);
        }
        for(UserStockLog userStockLog : userStockLogs){
            userStockLogDTOS.add(mapUserStockLogToUserStockLogDTO(userStockLog));
        }
        return userStockLogDTOS;

    }
}
