package com.example.springserver.service;

import com.example.springserver.dto.StockDTO;
import com.example.springserver.model.Stock;
import com.example.springserver.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    public StockRepository stockRepository;

    public StockDTO mapStockToStockDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setStock_id(stock.getStock_id());
        stockDTO.setName(stock.getName());
        stockDTO.setDescription(stock.getDescription());
        return stockDTO;
    }

    public Stock mapStockDTOToStock(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setStock_id(stockDTO.getStock_id());
        stock.setName(stockDTO.getName());
        stock.setDescription(stockDTO.getDescription());

        return stock;
    }

    public List<StockDTO> getAllStocks(){
        List<StockDTO> stockDTOS = new ArrayList<>();
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks){
            stockDTOS.add(mapStockToStockDTO(stock));
        }
        return stockDTOS;
    }

    public StockDTO getStock(int id){
        Stock stock = stockRepository.getOne(id);
        StockDTO stockDTO = new StockDTO();
        if(stock != null){
            stockDTO = mapStockToStockDTO(stock);
        }
        return stockDTO;
    }

    public StockDTO addStock(StockDTO stockDTO){
        Stock stock = mapStockDTOToStock(stockDTO);
        Stock newStock = stockRepository.save(stock);
        return mapStockToStockDTO(newStock);
    }

    public StockDTO editStock(StockDTO stockDTO){
        Stock stock = stockRepository.getOne(stockDTO.getStock_id());
        stock.setName(stockDTO.getName());
        stock.setDescription(stockDTO.getDescription());
        Stock newStock = stockRepository.save(stock);
        return mapStockToStockDTO(newStock);
    }

    public StockDTO deleteStock(StockDTO stockDTO){
        Stock stock = stockRepository.getOne(stockDTO.getStock_id());
        stockRepository.delete(stock);
        return stockDTO;
    }

}
