package abm.abmjava.repositories;

import abm.abmjava.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartsRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByClientName(String  name);
}
