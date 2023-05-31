package com.platzi.Market_new.product.controller;

import com.platzi.Market_new.product.dto.ProductDto;
import com.platzi.Market_new.product.entity.Producto;
import com.platzi.Market_new.product.service.ProductServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImple productServiceImple;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>>  getAll(){
        return new ResponseEntity<>(productServiceImple.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") int idProducto ){
        return productServiceImple.getProductId(idProducto).map(productDto -> new ResponseEntity<>(productDto, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byCategory/{idCategoria}")
    public  ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable("idCategoria") int idCategoria){
        List<ProductDto > productDtos = productServiceImple.getProductByCategory(idCategoria);
        if (productDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/save/")
    public ResponseEntity<ProductDto> save (@Valid @RequestBody ProductDto productDto, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+"usted ah ingresado: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productServiceImple.saveProduct(productDto), HttpStatus.CREATED);
    }
}
