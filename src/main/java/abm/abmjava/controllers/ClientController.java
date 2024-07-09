package abm.abmjava.controllers;

import abm.abmjava.entities.Client;
import abm.abmjava.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    @Autowired
    private ClientsService clientsService;

    // Endpoint para crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        try {
            // Guarda el cliente en la base de datos y retorna la respuesta con el cliente creado
            return  new ResponseEntity<>(clientsService.save(client), HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo de errores en caso de falla al crear el cliente
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Client>> getAllPersons(){
        try{
            // Busca todos los clientes en la base de datos
            List<Client> client = clientsService.findAll();
            if (!client.isEmpty()){
                // Retorna la lista de clientes si hay al menos uno encontrado
                return ResponseEntity.ok(client);
            }else{
                // Retorna estado 204 No Content si no hay clientes encontrados
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            // Manejo de errores en caso de falla al obtener todos los clientes
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable Long id){
        try {
            // Busca un cliente por su ID en la base de datos
            Optional<Client> client = clientsService.findById(id);
            if(client.isPresent()){
                // Retorna el cliente encontrado si existe
                return  ResponseEntity.ok(client.get());
            }else {
                // Retorna estado 404 Not Found si no se encuentra el cliente
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            // Manejo de errores en caso de falla al obtener el cliente por ID
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para actualizar un cliente por su ID
    @PatchMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id,@RequestBody Client data){
        try{
            // Busca un cliente por su ID en la base de datos
            Optional<Client> OptionalClient = clientsService.findById(id);
            if (OptionalClient.isPresent()){
                // Actualiza los campos del cliente con los nuevos datos recibidos
                Client client = OptionalClient.get();
                client.setName(data.getName());
                client.setLastName(data.getLastName());
                client.setDocNumber(data.getDocNumber());
                client.setAge(data.getAge());
                client.setProducts(data.getProducts());
                // Guarda y retorna el cliente actualizado
                return ResponseEntity.ok(clientsService.save(client));
            }else{
                // Retorna estado 404 Not Found si no se encuentra el cliente a actualizar
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            // Manejo de errores en caso de falla al actualizar el cliente
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para eliminar un cliente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id) {
        try {
            // Busca un cliente por su ID en la base de datos
            Optional<Client> optionalClient = clientsService.findById(id);
            if (optionalClient.isPresent()) {
                // Elimina el cliente encontrado
                clientsService.deleteById(id);
                // Retorna una respuesta indicando que el cliente fue eliminado con éxito
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return ResponseEntity.ok(response);
            } else {
                // Retorna estado 404 Not Found si no se encuentra el cliente a eliminar
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Manejo de errores en caso de falla al eliminar el cliente
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

