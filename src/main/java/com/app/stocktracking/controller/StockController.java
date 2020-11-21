package com.app.stocktracking.controller;

import com.app.stocktracking.entity.Stock;
import com.app.stocktracking.repository.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
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
}
