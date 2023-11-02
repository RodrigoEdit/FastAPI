package com.javawhizz.App.product;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
 
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



    
    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }




    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> rest = productRepository.findProductByName(product.getName());
        HashMap<String,Object> map = new HashMap<>();
        
        if (rest.isPresent()) {
            map.put("error",true);
            map.put("Menssage", "Ya existe un producto con ese nombre");
            return new ResponseEntity<>(
                map,
                HttpStatus.CONFLICT
            );
        }
        productRepository.save(product);
        map.put("Se Guardo Correctamente",product);
        return new ResponseEntity<>(
                map,
                HttpStatus.CREATED
            );
    }




    public ResponseEntity<Object> actualizarProducto(Product product) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Product> existingProduct = productRepository.findById(product.getId());

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setFecha(product.getFecha());

            productRepository.save(updatedProduct);

            map.put("Actualizado Correctamente", updatedProduct);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("error", true);
            map.put("Menssage", "Producto no encontrado");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Object> eliminarProducto(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Product> existingProduct = productRepository.findById(id);
    
        if (existingProduct.isPresent()) {
            productRepository.deleteById(id);
            map.put("Eliminado Correctamente", id);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("error", true);
            map.put("Menssage", "Producto no encontrado");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
