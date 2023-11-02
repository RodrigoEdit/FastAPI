package com.javawhizz.App.product;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductService productService;



    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> geProducts(){
        return productService.getProducts();
    }
    
    @PostMapping
    public ResponseEntity<Object> registrarProducto(@RequestBody Product product){
        return this.productService.newProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable Long id, @RequestBody Product product) {
    product.setId(id);
    return productService.actualizarProducto(product);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable Long id) {
    return productService.eliminarProducto(id);
    
    }
}
