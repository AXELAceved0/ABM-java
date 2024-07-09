package abm.abmjava.repositories;

import abm.abmjava.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
List<Client> findByName(String name);
}
