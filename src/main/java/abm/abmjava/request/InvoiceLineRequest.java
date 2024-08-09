package abm.abmjava.request;

public class InvoiceLineRequest {
    private int cantidad;
    private abm.abmjava.requests.ProductoRequest producto;

    // Getters y Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public abm.abmjava.requests.ProductoRequest getProducto() {
        return producto;
    }

    public void setProducto(abm.abmjava.requests.ProductoRequest producto) {
        this.producto = producto;
    }
}
