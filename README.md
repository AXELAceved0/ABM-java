# Proyecto ABM en Java

Este proyecto implementa un sistema ABM (Alta, Baja, Modificación) utilizando Java con Spring Boot y Hibernate para la capa de persistencia de datos.

## Entities

* **Cart Entity**: Representa el carrito de compras de un cliente.
* **CartItem Entity**: Representa un ítem dentro de un carrito.
* **Client Entity**: Representa un cliente.
* **Invoice Entity**: Representa una factura generada para un cliente.
* **InvoiceItem Entity**: Representa un ítem dentro de una factura.
* **Product Entity**: Representa un producto disponible.

## Repositories

* **CartRepository**: Repositorio para la entidad `Cart`.
* **CartItemRepository**: Repositorio para la entidad `CartItem`.
* **ClientRepository**: Repositorio para la entidad `Client`.
* **InvoiceRepository**: Repositorio para la entidad `Invoice`.
* **InvoiceItemRepository**: Repositorio para la entidad `InvoiceItem`.
* **ProductRepository**: Repositorio para la entidad `Product`.

## Services

* **CartService**: Servicio para manejar operaciones relacionadas con el carrito.
* **ClientService**: Servicio para manejar operaciones relacionadas con clientes.
* **InvoiceService**: Servicio para manejar operaciones relacionadas con facturas.
* **ProductService**: Servicio para manejar operaciones relacionadas con productos.

## Controllers

* **CartController**: Controlador para las operaciones CRUD relacionadas con el carrito.
* **ClientController**: Controlador para las operaciones CRUD relacionadas con clientes.
* **InvoiceController**: Controlador para las operaciones CRUD relacionadas con facturas.
* **ProductController**: Controlador para las operaciones CRUD relacionadas con productos.

