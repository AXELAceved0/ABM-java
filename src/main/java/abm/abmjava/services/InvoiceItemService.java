package abm.abmjava.services;

import abm.abmjava.entities.InvoiceItem;
import abm.abmjava.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceItemService {

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    public List<InvoiceItem> findAll() {
        return invoiceItemRepository.findAll();
    }

    public Optional<InvoiceItem> findById(Long id) {
        return invoiceItemRepository.findById(id);
    }

    public InvoiceItem save(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }

    public void deleteById(Long id) {
        invoiceItemRepository.deleteById(id);
    }
}
