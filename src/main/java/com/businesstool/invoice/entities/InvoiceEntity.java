package com.businesstool.invoice.entities;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Invoice_table", indexes = {}, uniqueConstraints = {})
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client")
    private String client;
    @Column(name = "items")
    private String items;
    @Column(name = "total")
    private Double total;
    @Column(name = "status")
    private String status;
}
