package abm.abmjava.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("items")
    @Getter @Setter private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("cartItems")
    @Getter @Setter private Product product;

    @Getter @Setter private Integer quantity;
}
