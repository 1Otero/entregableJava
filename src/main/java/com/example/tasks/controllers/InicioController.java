package com.example.tasks.controllers;

import com.example.tasks.modelos.Producto;
import com.example.tasks.services.ProductoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/api")
@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
public class InicioController {
    private List<Producto> Productos;
    @Autowired
    private ProductoService SProducto;

    public InicioController(){
        if (Productos == null){
            Productos = Arrays.asList(new Producto(1l,"Manzana Verde","Manzana Verde de buena calidad",600),
            new Producto(2l,"Manzana Roja","Manzana Roja de buena calidad",500));
        }
    }
    @GetMapping("/")
    public List<Producto> ConseguirTodos(){
        List<Producto> listaProductos = this.SProducto.conseguirTodos();
        return listaProductos;
    }
    @GetMapping("/producto/{id}")
    //public Producto ConseguirProducto( int Id){

    public ResponseEntity ConseguirProducto(@RequestParam(defaultValue = "0", value = "id") String Id){
        try{
            /*Producto p = Productos.stream().filter((e) -> {
                return e.getId() == Id;
            }).findFirst().get();*/
            Producto producto = this.SProducto.conseguirProducto(Long.parseLong(Id));
            System.out.println("Producto " + producto.getNombre());
            //return ResponseEntity.status(400).headers("MyResponseHeader","MyValue") body(producto);
            //return new ResponseEntity<Producto>(producto,null, HttpStatus.FOUND);
            return ResponseEntity.ok(producto);
        }catch (Exception e){
            //return ResponseEntity.status(404).body("No Existen Datos");
            HashMap<String,String> error = new HashMap<>();
            error.put("error","No hay datos");
            return ResponseEntity.ok(error);

        }
    }

    @PutMapping("/modificarProducto/")

    //public ResponseEntity ModificarProducto(@RequestParam(defaultValue = "0", value = "id") Long id, @RequestBody Producto producto){
    public ResponseEntity ModificarProducto(@RequestBody Producto producto){
        if (producto != null){
            //return ResponseEntity.status(201).body(new Producto(producto.getId(),producto.getNombre(),producto.getDescription(),producto.getPrecio()));
            System.out.println("datos: ");
            System.out.println(producto.getNombre());
            //producto.setId(id);
            Producto producto1 = this.SProducto.modificarProducto(producto);
            return ResponseEntity.status(200).body(producto1);
        }else {
            return ResponseEntity.status(404).body("No Existe!");
        }
    }

    @PostMapping("/agregarProducto")
    public ResponseEntity agregarProducto(@RequestBody Producto p){
        if (p != null){
            Producto producto = this.SProducto.agregarProducto(p);
            return ResponseEntity.status(201).body(producto);
        }else {
            HashMap<String,String> error = new HashMap<>();
            error.put("error","No se pudo crear el producto");
            //return ResponseEntity.status(400).body("El producto no se puede crear ya que no ingreso datos");
            return ResponseEntity.ok(error);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteProduct(@RequestParam(defaultValue = "0", value = "id") String id){
       try{
           Producto p = this.SProducto.deleteProduct(Long.parseLong(id));
           return ResponseEntity.ok(p);
       }catch (Exception e){
           HashMap<String,String> error = new HashMap<>();
           error.put("error","No se logro eliminar este producto");
           return ResponseEntity.ok(error);
       }
    }
}
