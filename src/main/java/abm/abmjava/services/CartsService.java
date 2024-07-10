package abm.abmjava.services;

import abm.abmjava.entities.Cart;
import abm.abmjava.repositories.CartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsService {

    @Autowired
    CartsRepository cartsRepository;

    public List<Cart> findAll(){
        return cartsRepository.findAll();
    }

    public Optional<Cart> findById(Long id){
        return cartsRepository.findById(id);
    }

    public Cart save(Cart cart){
        return cartsRepository.save(cart);
    }

    public  void deleteById(Long id){
        cartsRepository.deleteById(id);
    }

    public List<Cart> findByClientName(String name){
        return cartsRepository.findByClientName(name);
    }
}
