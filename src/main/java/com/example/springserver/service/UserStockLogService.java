package com.example.springserver.service;


import com.example.springserver.dto.UserStockLogDTO;
import com.example.springserver.model.Stock;
import com.example.springserver.model.User;
import com.example.springserver.model.UserStockLog;
import com.example.springserver.repository.StockRepository;
import com.example.springserver.repository.UserRepository;
import com.example.springserver.repository.UserStockLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserStockLogService {
    @Autowired
    public UserService userService;

    @Autowired
    public StockService stockService;

    @Autowired
    public StockRepository stockRepository;

    @Autowired
    public UserStockLogRepository userStockLogRepository;

    @Autowired
    public UserRepository userRepository;


    public UserStockLogDTO mapUserStockLogToUserStockLogDTO(UserStockLog UserStockLog) {
        UserStockLogDTO userStockLogDTO = new UserStockLogDTO();
        userStockLogDTO.setUser_stock_id(UserStockLog.getUser_stock_id());
        userStockLogDTO.setPrice(UserStockLog.getPrice());
        userStockLogDTO.setShares(UserStockLog.getShares());
        userStockLogDTO.setCreated(UserStockLog.getCreated());
        userStockLogDTO.setSpending(UserStockLog.getSpendings());
        userStockLogDTO.setProfit(UserStockLog.getProfit());
        userStockLogDTO.setUserDTO(userService.mapUserToUserDTO(UserStockLog.getUser()));
        userStockLogDTO.setStockDTO(stockService.mapStockToStockDTO(UserStockLog.getStock()));

        return userStockLogDTO;
    }

    // gives all the stock details that user owns
    public List<UserStockLogDTO> getAllUserLogs(int user_id) {
        User user = userRepository.getOne(user_id);
        List<UserStockLogDTO> userStockLogDTOS = new ArrayList<>();
        List<UserStockLog> userStockLogs = new ArrayList<>();
        if (user != null) {
            userStockLogs = userStockLogRepository.findAllByUser(user_id);
        }
        for (UserStockLog userStockLog : userStockLogs) {
            userStockLogDTOS.add(mapUserStockLogToUserStockLogDTO(userStockLog));
        }
        return userStockLogDTOS;
    }

    /// has to check stock amount

    // add stock data to user
    // has add exceptions
    // 1 - to done 0 - to not sufficient assets

    public Integer addStockToUser(int user_id, int stock_id, int stock_shares, Double stock_price) {
        UserStockLog userStockLog = userStockLogRepository.findByUserIdAndStockId(user_id, stock_id);

        // check user assets
        User user = userRepository.getOne(user_id);
        Stock stock = stockRepository.getOne(stock_id);
        if (user == null) {
            throw new NullPointerException();
        }
        if (stock == null) {
            throw new NullPointerException();
        }
        Double price = stock_shares * stock_price;
        Double totalAssets = user.getAssets();
        if (price <= totalAssets) {
            user.setAssets(totalAssets - price);
        } else {
            return 0;
        }
        userRepository.save(user);
        if (userStockLog == null) {
            userStockLog = new UserStockLog();
            // has to add error validation
            userStockLog.setUser(user);

            // has to add error validation
            userStockLog.setStock(stock);

            userStockLog.setPrice(stock_price);
            userStockLog.setSpendings(price);
            userStockLog.setShares(stock_shares);
            userStockLog.setCreated(new Date());

            userStockLogRepository.save(userStockLog);
        }
        return 1;
    }

    // if buy - 1 sell - 0 <--- buyOrSell value
    public Integer updateUserStock(int user_id, int stock_id, int stock_shares, Double stock_price, int buyOrSell) {
        // check user assets
        User user = userRepository.getOne(user_id);
        Stock stock = stockRepository.getOne(stock_id);
        if (user == null) {
            throw new NullPointerException();
        }
        if (stock == null) {
            throw new NullPointerException();
        }
        UserStockLog userStockLog = userStockLogRepository.findByUserIdAndStockId(user_id, stock_id);
        if (userStockLog == null) {
            throw new NullPointerException();
        }

        // buy
        if (buyOrSell == 1) {
            Double price = stock_shares * stock_price;
            Double totalAssets = user.getAssets();
            if (price <= totalAssets) {
                user.setAssets(totalAssets - price);
            } else {
                return 0;
            }
            if (userStockLog != null) {
                // has to add error validation
                int old_shares = userStockLog.getShares();
                Double old_value = userStockLog.getSpendings();
                userStockLog.setUser(user);
                // has to add error validation
                userStockLog.setStock(stock);
                userStockLog.setSpendings(price + old_value);
                userStockLog.setPrice((price + old_value)/(old_shares + stock_shares));
                userStockLog.setShares(old_shares + stock_shares);
                userStockLog.setModified(new Date());
            }
        }
        // sell
        else if(buyOrSell == 0){
            Double price = stock_shares * stock_price;
            Double totalAssets = user.getAssets();
            user.setAssets(totalAssets + price);
            if (userStockLog != null) {
                // has to add error validation
                userStockLog.setUser(user);
                int old_shares = userStockLog.getShares();
                Double old_spendings = userStockLog.getSpendings();
                Double old_profit = userStockLog.getProfit();
                // has to add error validation
                userStockLog.setStock(stock);

                if (old_shares < stock_shares) {
                    throw new IllegalArgumentException();
                }else if (old_shares == stock_shares){
                    userStockLog.setPrice(0.0);
                }
                if(old_spendings < price){
                    userStockLog.setSpendings(0.0);
                    userStockLog.setProfit(price-old_spendings + old_profit);
                }else {
                    userStockLog.setSpendings(old_spendings - price);
                }
                userStockLog.setShares(old_shares - stock_shares);
                userStockLog.setModified(new Date());
            }
        }else{
            throw new IllegalArgumentException();
        }
        userRepository.save(user);
        userStockLogRepository.save(userStockLog);
        return 1;
    }
}
