package com.app.stocktracking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "STOCK")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Stock {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "stock_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "hibernate_sequence", value = "stock_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "stock_id_seq")}
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_id_seq")
    private Integer id;

    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;
}
