package com.businesstool.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.businesstool.invoice.dtos.InvoiceDto;
import com.businesstool.invoice.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAllInvoices();
       //return "Work in Progress try again later ";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDTO) {
        return invoiceService.createInvoice(invoiceDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceDto updateInvoice(@PathVariable Long id, @RequestBody InvoiceDto invoiceDTO) {
        return invoiceService.updateInvoice(id, invoiceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}
