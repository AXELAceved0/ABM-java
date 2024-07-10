package abm.abmjava.controllers;

import abm.abmjava.entities.Cart;
import abm.abmjava.services.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    CartsService cartsService;

    // Endpoint para crear un nuevo carrito
    @PostMapping
    public ResponseEntity<Cart> create(@RequestBody Cart cart) {
        try {
            // Guarda el nuevo carrito y retorna una respuesta con estadoo (CREATED)
            return new ResponseEntity<>(cartsService.save(cart), HttpStatus.CREATED);
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener todos los carritos
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        try {
            // Obtiene la lista de todos los carritos
            List<Cart> carts = cartsService.findAll();
            // Si la lista no está vacía, retorna los carritos con estado
            if (!carts.isEmpty()) {
                return ResponseEntity.ok(carts);
            } else {
                // Si la lista está vacía, retorna una respuesta con estado
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener un carrito por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCartById(@PathVariable Long id) {
        try {
            // Busca el carrito por su ID
            Optional<Cart> cart = cartsService.findById(id);
            // Si el carrito está presente, retorna el carrito con estado
            if (cart.isPresent()) {
                return ResponseEntity.ok(cart.get());
            } else {
                // Si el carrito no está presente, retorna una respuesta con estado
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para actualizar un carrito por su ID
    @PatchMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart data) {
        try {
            // Busca el carrito por su ID
            Optional<Cart> optionalCart = cartsService.findById(id);
            // Si el carrito está presente, actualiza sus datos y guarda los cambios
            if (optionalCart.isPresent()) {
                Cart cart = optionalCart.get();
                cart.setClient(data.getClient());
                cart.setItems(data.getItems());
                return ResponseEntity.ok(cartsService.save(cart));
            } else {
                // Si el carrito no está presente, retorna una respuesta con estado
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para eliminar un carrito por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        try {
            // Busca el carrito por su ID
            Optional<Cart> optionalCart = cartsService.findById(id);
            // Si el carrito está presente, lo elimina y retorna una respuesta con estado
            if (optionalCart.isPresent()) {
                cartsService.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                // Si el carrito no está presente, retorna una respuesta con estado
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado 5
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
