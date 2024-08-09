package abm.abmjava.controllers;

import abm.abmjava.entities.Invoice;
import abm.abmjava.request.InvoiceRequest;
import abm.abmjava.services.InvoiceCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceCreationService invoiceCreationService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequest request) {
        try {
            Invoice invoice = invoiceCreationService.createInvoice(request);
            return ResponseEntity.ok(invoice);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

