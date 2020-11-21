package com.app.stocktracking.controller;

import com.app.stocktracking.entity.Stock;
import com.app.stocktracking.repository.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/stock")
@RestController
public class StockController {

    @Autowired
    private StockDAO stockDAO;

    @GetMapping("/list")
    public Collection<Stock> list() {
        return stockDAO.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/findByStockCode/{stockCode}")
    Stock get(@PathVariable("stockCode") String stockCode) {
        return stockDAO.findByStockCode(stockCode);
    }

    @PostMapping("/save")
    Stock save(@RequestBody Stock stock) {
        Stock savedStock = stockDAO.findByStockCode(stock.getStockCode());
        if(savedStock != null && stock.getId() == null){
            savedStock.setQuantity(savedStock.getQuantity() + stock.getQuantity());
            return stockDAO.save(savedStock);
        }else {
            return stockDAO.save(stock);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        stockDAO.deleteById(id);
    }


}
