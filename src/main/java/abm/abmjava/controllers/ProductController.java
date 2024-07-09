package abm.abmjava.controllers;

import abm.abmjava.entities.Product;
import abm.abmjava.services.ClientsService;
import abm.abmjava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ClientsService clientsService;

    @Autowired
    private ProductService productService;

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        try {
            // Guarda el producto en la base de datos y retorna la respuesta con el producto creado
            return  new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            // Busca todos los productos en la base de datos
            List<Product> product = productService.findAll();
            if (!product.isEmpty()){
                // Retorna la lista de productos si hay al menos uno encontrado
                return ResponseEntity.ok(product);
            }else{
                // Retorna estado 204 No Content si no hay productos encontrados
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            // Manejo de errores en caso de falla al obtener todos los productos
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        try {
            // Busca un producto por su ID en la base de datos
            Optional<Product> product = productService.findById(id);
            if(product.isPresent()){
                // Retorna el producto encontrado si existe
                return  ResponseEntity.ok(product.get());
            }else {
                // Retorna estado 404 Not Found si no se encuentra el producto
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            // Manejo de errores en caso de falla al obtener el producto por ID
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para actualizar un producto por su ID
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product data){
        try{
            // Busca un producto por su ID en la base de datos
            Optional<Product> OptionalProduct = productService.findById(id);
            if (OptionalProduct.isPresent()){
                // Actualiza los campos del producto con los nuevos datos recibidos

                Product product = OptionalProduct.get();
                product.setName(data.getName());
                product.setStock(data.getStock());
                product.setPrice(data.getPrice());
                product.setOwner(data.getOwner());
                // Guarda y retorna el producto actualizado
                return ResponseEntity.ok(productService.save(product));
            }else{
                // Guarda y retorna el producto actualizado
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            // Manejo de errores en caso de falla al actualizar el producto
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        try {
            // Busca un producto por su ID en la base de datos
            Optional<Product> optionalProduct = productService.findById(id);
            if (optionalProduct.isPresent()) {
                // Elimina el producto encontrado
                productService.deleteById(id);
                // Retorna una respuesta indicando que el producto fue eliminado con éxito
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return ResponseEntity.ok(response);
            } else {
                // Retorna estado 404 Not Found si no se encuentra el producto a eliminar
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Manejo de errores en caso de falla al eliminar el producto
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
