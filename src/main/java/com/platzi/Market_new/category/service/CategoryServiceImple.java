package com.platzi.Market_new.category.service;

import com.platzi.Market_new.category.dto.CategoryDto;
import com.platzi.Market_new.category.entity.Category;
import com.platzi.Market_new.category.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImple implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(category -> {
            return modelMapper.map(category, CategoryDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> getCategoryId(Integer idCategoria) {
        return Optional.ofNullable(categoryRepository.getCategoryId(idCategoria).map(category -> {
            return modelMapper.map(category, CategoryDto.class);
        }).orElseThrow(()-> new IllegalArgumentException("la categoria no existe"))
        );
    }

    @Override
    public CategoryDto updateCategory(Integer idCategoria, CategoryDto categoryDto) {
        if (!existsByCategoryDescrition(categoryDto.getDescripcion())) {
            return categoryRepository.findById(idCategoria).map(category -> {
                category.setDescripcion((categoryDto.getDescripcion() != null  )? categoryDto.getDescripcion() : category.getDescripcion());
                return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
            }).orElseThrow(() -> new IllegalArgumentException("no se encontro el producto para actualizar"));
        }
        throw new IllegalArgumentException("Ya existe una categoria con este nombre");
    }


    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        if (existsByCategoryDescrition(categoryDto.getDescripcion())){
            throw new IllegalArgumentException("Ya existe un producto con este nombre");
        }
        try {
            return modelMapper.map(categoryRepository.save(modelMapper.map(categoryDto, Category.class)),CategoryDto.class);
        }catch (Exception ex){
            throw new IllegalArgumentException("JSON mal estructurado");
        }
    }

    @Override
    public Boolean existsByCategoryDescrition(String descripcion) {
        return categoryRepository.existsByCategoryDescrition(descripcion);
    }

}
