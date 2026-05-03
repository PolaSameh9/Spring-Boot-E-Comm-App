package com.example.SpringEcom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.model.dto.ProductDTO.ProductResponse;

@Mapper()
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse productEntityToProductDto(Product product);
}
