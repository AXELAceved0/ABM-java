package abm.abmjava.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "Invoice_items")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class InvoiceItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private  Long id;

    @ManyToOne
    @Getter @Setter private Invoice invoice;

    @ManyToOne
    @Getter @Setter Product product;

    @Getter @Setter private Integer quantity;

    @Getter @Setter private  Double price;
}
