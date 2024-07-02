package com.businesstool.invoice.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.businesstool.invoice.Repository.InvoiceRepository;
import com.businesstool.invoice.dtos.InvoiceDto;
import com.businesstool.invoice.entities.InvoiceEntity;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository ;

    @Override
    public InvoiceDto getInvoiceById(Long id) {
      InvoiceEntity invoice =invoiceRepository.findById(id).get();
      return new InvoiceDto(invoice.getId(),invoice.getClient(),invoice.getItems(),invoice.getTotal(),invoice.getStatus());
    }

    @Override
    public InvoiceDto createInvoice(InvoiceDto invoiceDTO) {
        if (invoiceDTO == null) {
            throw new IllegalArgumentException("Invoice cannot be null");
        }
       InvoiceEntity invoice = new InvoiceEntity();
       invoice.setClient(invoiceDTO.getClient());
       invoice.setItems(invoiceDTO.getItems());
       invoice.setTotal(invoiceDTO.getTotal()); 
       invoice.setStatus(invoiceDTO.getStatus());
       InvoiceEntity savedInvoice = new InvoiceEntity();
    try {
        savedInvoice = invoiceRepository.save(invoice);

        Long id = savedInvoice.getId();
        String client = savedInvoice.getClient();
        String items = savedInvoice.getItems();
        double total = savedInvoice.getTotal();
        String status = savedInvoice.getStatus();

    if (id == null || client == null || items == null || status == null) {
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.severe("One of the fields in savedInvoice is null: " +
                      "id=" + id + ", client=" + client + ", items=" + items + ", total=" + total + ", status=" + status);
        throw new RuntimeException("One of the fields in savedInvoice is null.");
    }

    } catch (Exception e) {
        e.printStackTrace();
    }
       return new InvoiceDto(savedInvoice.getId(),savedInvoice.getClient(),savedInvoice.getItems(),savedInvoice.getTotal(),savedInvoice.getStatus());

    }

    @Override
    public InvoiceDto updateInvoice(Long id, InvoiceDto invoiceDTO) {
        InvoiceEntity invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setClient(invoiceDTO.getClient());
        invoice.setItems(invoiceDTO.getItems());
        invoice.setTotal(invoiceDTO.getTotal());
        invoice.setStatus(invoiceDTO.getStatus());
        invoice.setId(id);
        InvoiceEntity savedInvoice = new InvoiceEntity();
        try {
            savedInvoice = invoiceRepository.save(invoice);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new InvoiceDto(savedInvoice.getId(),savedInvoice.getClient(),savedInvoice.getItems(),savedInvoice.getTotal(),savedInvoice.getStatus());
    }

    @Override
    public List<InvoiceDto> getAllInvoices() {
       return invoiceRepository.findAll()
       .stream().map(invoice -> new InvoiceDto(invoice.getId(),invoice.getClient(),invoice.getItems(),invoice.getTotal(),invoice.getStatus()))
       .collect(Collectors.toList());
    }

    @Override
    public void deleteInvoice(Long id) {
        Optional<InvoiceEntity> invoiceOpt = invoiceRepository.findById(id);
        if (invoiceOpt.isPresent()) {
            invoiceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Invoice not found");
        }
    }

    public InvoiceDto saveInvoice(InvoiceEntity invoice) {
    InvoiceEntity savedInvoice = invoiceRepository.save(invoice);

    Long id = savedInvoice.getId();
    String client = savedInvoice.getClient();
    String items = savedInvoice.getItems();
    double total = savedInvoice.getTotal();
    String status = savedInvoice.getStatus();

    if (id == null || client == null || items == null || status == null) {
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.severe("One of the fields in savedInvoice is null: " +
                      "id=" + id + ", client=" + client + ", items=" + items + ", status=" + status);
        throw new RuntimeException("One of the fields in savedInvoice is null.");
    }

    return new InvoiceDto(id, client, items, total, status);
}
}
