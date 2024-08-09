package abm.abmjava.services;

import abm.abmjava.entities.Client;
import abm.abmjava.entities.Invoice;
import abm.abmjava.entities.InvoiceItem;
import abm.abmjava.entities.Product;
import abm.abmjava.repositories.InvoiceItemRepository;
import abm.abmjava.repositories.InvoiceRepository;
import abm.abmjava.repositories.ProductsRepository;
import abm.abmjava.request.InvoiceRequest;
import abm.abmjava.request.InvoiceLineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceCreationService {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private ExternalDateService externalDateService;

    public Invoice createInvoice(InvoiceRequest request) throws Exception {
        // Validar existencia del cliente
        Client client = clientsService.findById(request.getCliente().getClienteid())
                .orElseThrow(() -> new Exception("Cliente no existe"));

        // Validaciones y procesamiento de productos
        double total = 0.0;
        int totalQuantity = 0;
        List<InvoiceItem> items = new ArrayList<>();

        for (InvoiceLineRequest line : request.getLineas()) {
            Product product = productService.findById(line.getProducto().getProductoid())
                    .orElseThrow(() -> new Exception("Producto no existe"));
            if (product.getStock() < line.getCantidad()) {
                throw new Exception("Stock insuficiente para el producto " + product.getName());
            }

            // Reducir el stock
            product.setStock(product.getStock() - line.getCantidad());
            productService.save(product);

            // Crear el InvoiceItem
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProduct(product);
            invoiceItem.setQuantity(line.getCantidad());
            invoiceItem.setPrice(product.getPrice().doubleValue()); // Asegúrate de convertir a Double si es necesario
            items.add(invoiceItem);

            // Calcular totales
            total += product.getPrice() * line.getCantidad();
            totalQuantity += line.getCantidad();
        }

        // Obtener la fecha del comprobante
        Date invoiceDate = externalDateService.getCurrentDate();

        // Crear y guardar el comprobante
        Invoice invoice = new Invoice();
        invoice.setClient(client);
        invoice.setDate(invoiceDate);
        invoice.setItems(items);
        invoice.setTotal(total);
        invoiceService.save(invoice);

        // Guardar los items del comprobante
        for (InvoiceItem item : items) {
            item.setInvoice(invoice);
            invoiceItemService.save(item);
        }

        return invoice;
    }
}


