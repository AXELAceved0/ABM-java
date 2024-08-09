package abm.abmjava.request;

import java.util.List;

public class InvoiceRequest {
    private ClienteRequest cliente;
    private List<InvoiceLineRequest> lineas;

    // Getters y Setters
    public ClienteRequest getCliente() {
        return cliente;
    }

    public void setCliente(ClienteRequest cliente) {
        this.cliente = cliente;
    }

    public List<InvoiceLineRequest> getLineas() {
        return lineas;
    }

    public void setLineas(List<InvoiceLineRequest> lineas) {
        this.lineas = lineas;
    }
}
