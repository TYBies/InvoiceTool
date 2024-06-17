package com.businesstool.invoice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.businesstool.invoice.Repository.InvoiceRepository;
import com.businesstool.invoice.dtos.InvoiceDto;
import com.businesstool.invoice.entities.InvoiceEntity;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    private InvoiceRepository invoiceRepository ;

    @Override
    public InvoiceDto getInvoiceById(Long id) {
      InvoiceEntity invoice =invoiceRepository.findById(id).get();
      return new InvoiceDto(invoice.getId(),invoice.getClient(),invoice.getItems(),invoice.getTotal(),invoice.getStatus());
    }

    @Override
    public InvoiceDto createInvoice(InvoiceDto invoiceDTO) {
       InvoiceEntity invoice = new InvoiceEntity();
       invoice.setClient(invoiceDTO.getClient());
       invoice.setItems(invoiceDTO.getItems());
       invoice.setTotal(invoiceDTO.getTotal());
       invoice.setStatus(invoiceDTO.getStatus());
       InvoiceEntity savedInvoice = new InvoiceEntity();
    try {
        savedInvoice = invoiceRepository.save(invoice);
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
       return invoiceRepository.findAll().stream().map(invoice -> new InvoiceDto(invoice.getId(),invoice.getClient(),invoice.getItems(),invoice.getTotal(),invoice.getStatus()))
       .collect(Collectors.toList());
    }

    @Override
    public void deleteInvoice(Long id) {
        InvoiceEntity
         invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoiceRepository.delete(invoice);
    }
    

}
