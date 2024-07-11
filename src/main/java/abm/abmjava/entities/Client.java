package abm.abmjava.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity @Table(name = "Clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;

    @Getter @Setter private String lastName;

    @Getter @Setter private Integer docNumber;

    @Getter @Setter private Integer age;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<Product> products;

    @OneToOne(mappedBy = "client")
    @JsonIgnoreProperties("owner")
    @Getter @Setter private Cart cart;

}
