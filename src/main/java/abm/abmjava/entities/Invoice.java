package abm.abmjava.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity @Table(name = "invoices")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private Date date;

    @ManyToOne
    @Getter @Setter private Client client;

    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<InvoiceItem> items;

    @Getter @Setter private Double total;
}
