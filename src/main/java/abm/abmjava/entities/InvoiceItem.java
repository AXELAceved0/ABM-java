package abm.abmjava.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invoice_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @Getter @Setter
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter
    private Product product;

    @Getter @Setter
    private Integer quantity;

    @Getter @Setter
    private Double price; // Asegúrate de que sea Double
}

