package abm.abmjava.controllers;

import abm.abmjava.entities.CartItem;
import abm.abmjava.services.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cart-items")
public class CartItemController {

    @Autowired
    private CartItemsService cartItemsService;

    // Endpoint para crear un nuevo elemento del carrito
    @PostMapping
    public ResponseEntity<CartItem> create(@RequestBody CartItem cartItem) {
        try {
            // Guarda el nuevo elemento del carrito y retorna una respuesta con estado (CREATED)
            return new ResponseEntity<>(cartItemsService.save(cartItem), HttpStatus.CREATED);
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener todos los elementos del carrito
    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        try {
            // Obtiene la lista de todos los elementos del carrito
            List<CartItem> cartItems = cartItemsService.findAll();
            // Si la lista no está vacía, retorna los elementos con estado
            if (!cartItems.isEmpty()) {
                return ResponseEntity.ok(cartItems);
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

    // Endpoint para obtener un elemento del carrito por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCartItemById(@PathVariable Long id) {
        try {
            // Busca el elemento del carrito por su ID
            Optional<CartItem> cartItem = cartItemsService.findById(id);
            // Si el elemento está presente, retorna el elemento con estado
            if (cartItem.isPresent()) {
                return ResponseEntity.ok(cartItem.get());
            } else {
                // Si el elemento no está presente, retorna una respuesta con estado
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para actualizar un elemento del carrito por su ID
    @PatchMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem data) {
        try {
            // Busca el elemento del carrito por su ID
            Optional<CartItem> optionalCartItem = cartItemsService.findById(id);
            // Si el elemento está presente, actualiza sus datos y guarda los cambios
            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                cartItem.setProduct(data.getProduct());
                cartItem.setQuantity(data.getQuantity());
                cartItem.setCart(data.getCart());
                return ResponseEntity.ok(cartItemsService.save(cartItem));
            } else {
                // Si el elemento no está presente, retorna una respuesta con estado
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para eliminar un elemento del carrito por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCartItem(@PathVariable Long id) {
        try {
            // Busca el elemento del carrito por su ID
            Optional<CartItem> optionalCartItem = cartItemsService.findById(id);
            // Si el elemento está presente, lo elimina y retorna una respuesta con estado
            if (optionalCartItem.isPresent()) {
                cartItemsService.deleteById(id);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return ResponseEntity.ok(response);
            } else {
                // Si el elemento no está presente, retorna una respuesta con estado
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción y retorna una respuesta con estado
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
