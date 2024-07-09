package abm.abmjava.services;

import abm.abmjava.entities.Client;
import abm.abmjava.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {

    @Autowired private ClientsRepository clientsRepository;

    public List<Client> findAll(){
        return clientsRepository.findAll();
    }

    public Optional<Client> findById(Long id){
        return clientsRepository.findById(id);
    }

    public Client save(Client client){
        return clientsRepository.save(client);
    }

    public  void deleteById(Long id){
        clientsRepository.deleteById(id);
    }

    public List<Client> findByName(String name){
        return clientsRepository.findByName(name);
    }
}
