package com.platzi.Market_new.product.service;

import com.platzi.Market_new.product.dto.ProductDto;
import com.platzi.Market_new.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImple implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> getAllProduct(){
        return productRepository.findAll().stream().map(product -> {
            return modelMapper.map(product,ProductDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> getProductId(Integer idProducto) {
        return Optional.empty();
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(Integer idProducto, ProductDto productDto) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Integer idProducto) {
        if(productRepository.findById(idProducto).isPresent()){
            productRepository.deleteById(idProducto);
            return true;
        }
        return false;
    }


}