package abm.abmjava.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "Items")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @Getter @Setter private Cart cart;

    @ManyToOne
    @Getter @Setter private  Product product;

    @Getter @Setter private int quantity;

}
