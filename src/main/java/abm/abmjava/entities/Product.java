package abm.abmjava.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;

    @Getter @Setter private Integer stock;

    @Getter @Setter private Integer price;

   @ManyToOne @JoinColumn(name = "owner_id")
   @JsonIgnoreProperties("products")
    @Getter @Setter private Client owner;
}
