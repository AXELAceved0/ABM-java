package abm.abmjava.services;

import abm.abmjava.entities.CartItem;
import abm.abmjava.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemsService {
    @Autowired
    CartItemRepository cartItemsRepository;

    public CartItem save(CartItem cartItem){
        return cartItemsRepository.save(cartItem);
    }

    public List<CartItem> findAll(){
        return cartItemsRepository.findAll();
    }

    public Optional<CartItem> findById(Long id){
        return cartItemsRepository.findById(id);
    }

    public void deleteById(Long id){
        cartItemsRepository.deleteById(id);
    }
}
