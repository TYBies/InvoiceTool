package com.businesstool.invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.businesstool.invoice.Repository.InvoiceRepository;
import com.businesstool.invoice.dtos.InvoiceDto;
import com.businesstool.invoice.entities.InvoiceEntity;
import com.businesstool.invoice.service.InvoiceServiceImpl;

public class InvoiceServiceTests {
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInvoices() {
        List<InvoiceEntity> invoices = Arrays.asList(new InvoiceEntity(1L, "Test Client 1", "Test Invoice 1", 100.0, "pending"), new InvoiceEntity());
        when(invoiceRepository.findAll()).thenReturn(invoices);

        List<InvoiceDto> result = invoiceService.getAllInvoices();
        assertEquals(2, result.size());
    }

    @Test
    void testCreateInvoice() {
        InvoiceEntity invoice = new InvoiceEntity(1L, "TestClient", "Test Invoice", 100.0, "pending");
        when(invoiceRepository.save(any(InvoiceEntity.class))).thenReturn(invoice);

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setItems("Test Invoice");
        invoiceDto.setTotal(100.0);
        invoiceDto.setClient("TestClient");
        invoiceDto.setStatus("pending");

        InvoiceDto result = invoiceService.createInvoice(invoiceDto);
        assertNotNull(result);
        assertEquals("Test Invoice", result.getItems());

        verify(invoiceRepository).save(any(InvoiceEntity.class));

       
    }


    @Test
    void testUpdateInvoice() {
        InvoiceEntity invoice = new InvoiceEntity(1L, "Updated Client", "Updated Invoice", 150.0, "pending");
        when(invoiceRepository.findById(1L)).thenReturn(java.util.Optional.of(invoice));
        when(invoiceRepository.save(any(InvoiceEntity.class))).thenReturn(invoice);
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setItems("Updated Invoice");
        invoiceDto.setTotal(150.0);

        InvoiceDto result = invoiceService.updateInvoice(1L, invoiceDto);
        assertNotNull(result);
        assertEquals("Updated Invoice", result.getItems());
    }

    @Test
    void testDeleteInvoice() {
        // Arrange
        Long invoiceId = 1L;
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setId(invoiceId);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.deleteInvoice(invoiceId);

        // Assert
        verify(invoiceRepository).findById(invoiceId);
        verify(invoiceRepository).deleteById(invoiceId);
    }
}
