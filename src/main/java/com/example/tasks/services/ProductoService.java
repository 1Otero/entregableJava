package com.example.tasks.services;

import com.example.tasks.modelos.Producto;
import com.example.tasks.repositorios.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private IProductoRepository RProducto;

    public List<Producto> conseguirTodos(){
        System.out.println("Productos");
        List<Producto> lp = RProducto.findAll().stream().toList();
        for (Producto p:lp) {
            System.out.println("Producto: ");
            System.out.println(p.getNombre() + p.getPrecio());
        }
        return lp;
    }
    public Producto conseguirProducto(Long id){
        return RProducto.findById(id).get();
    }
    public Producto modificarProducto(Producto p){
        Producto prod = this.conseguirProducto(p.getId());
        if(prod != null){
            prod.setNombre(p.getNombre()==null?prod.getNombre():p.getNombre());
            prod.setDescription(p.getDescription()==null?prod.getDescription():p.getDescription());
            prod.setPrecio(p.getPrecio()<0?prod.getPrecio():p.getPrecio());
            return RProducto.save(prod);
        }else{
            return p;
        }

    }
    public Producto deleteProduct(Long id){
        Producto p = this.conseguirProducto(id);
        RProducto.deleteById(id);
        return p;
    }
    public Producto agregarProducto(Producto p){
        Producto pro = RProducto.save(p);
        System.out.println(pro.getNombre());
        return pro;
    }
}
