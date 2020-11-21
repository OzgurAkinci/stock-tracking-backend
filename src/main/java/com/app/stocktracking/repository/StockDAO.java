package com.app.stocktracking.repository;

import com.app.stocktracking.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface StockDAO extends JpaRepository<Stock, Integer> {
    Stock findByStockCode(String stockCode);
}
