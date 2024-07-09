package abm.abmjava.services;

import abm.abmjava.entities.Product;
import abm.abmjava.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductsRepository productsRepository;

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

        public Optional<Product> findById(Long id){
            return productsRepository.findById(id);
    }

    public void deleteById(Long id){
        productsRepository.deleteById(id);
    }

    public List<Product> findByName(String name){
      return productsRepository.findByName(name);
    }
}
