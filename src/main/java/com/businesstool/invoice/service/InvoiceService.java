package com.businesstool.invoice.service;

import java.util.List;

import com.businesstool.invoice.dtos.InvoiceDto;

public interface InvoiceService {
    InvoiceDto getInvoiceById(Long id);
    InvoiceDto createInvoice(InvoiceDto invoiceDTO);
    InvoiceDto updateInvoice(Long id, InvoiceDto invoiceDTO);
    List<InvoiceDto> getAllInvoices();
    void deleteInvoice(Long id);
}