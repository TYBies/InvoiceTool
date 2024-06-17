package com.businesstool.invoice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.businesstool.invoice.entities.InvoiceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    
}
