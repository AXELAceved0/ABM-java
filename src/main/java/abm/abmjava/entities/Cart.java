package abm.abmjava.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Table(name = "carts")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @Getter @Setter  private Client client;

    @OneToMany(mappedBy = "cart")
    @Getter @Setter private List<CartItem> items;

}
