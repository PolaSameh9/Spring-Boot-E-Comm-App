package com.example.SpringEcom.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringEcom.mapper.ProductMapper;
import com.example.SpringEcom.model.Category;
import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.model.dto.FilterDto;
import com.example.SpringEcom.model.dto.ProductDTO.ProductRequest;
import com.example.SpringEcom.model.dto.ProductDTO.ProductResponse;
import com.example.SpringEcom.model.dto.SortDto;
import com.example.SpringEcom.repo.CategoryRepo;
import com.example.SpringEcom.repo.ProductRepo;
import com.example.SpringEcom.specification.ProductSpecification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo CategoryRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageType(image.getOriginalFilename());
        product.setImageName(image.getName());
        product.setImageData(image.getBytes());

        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = CategoryRepo.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        } else {
            throw new RuntimeException("Category must be provided");
        }

        return productRepo.save(product);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }

    public Page<ProductResponse> searchProductsWithPagnationSortingAndFiltering(ProductRequest productRequest) {
        FilterDto filterDto = FilterDto.builder()
                                .categoryId(productRequest.getCategoryId())
                                .productPrice(productRequest.getProductPrice())
                                .build();

        List<SortDto> sortDtos = jsonStringToSortDto(productRequest.getSort());
        List<Sort.Order> orders = new ArrayList<>();

        if(sortDtos != null){
            for(SortDto sortDto : sortDtos){
                Sort.Direction direction = Objects.equals(sortDto.getDirection(), "desc") 
                    ? Sort.Direction.DESC : Sort.Direction.ASC;
                orders.add(new Sort.Order(direction, sortDto.getField()));
            }
        }
        PageRequest pageRequest = PageRequest.of(
            productRequest.getPage(),
            productRequest.getSize(),
            Sort.by(orders)
        );

        Specification<Product> specification = ProductSpecification.getSpecification(filterDto);
        Page<Product> products = productRepo.findAll(specification, pageRequest);
        return products.map(ProductMapper.INSTANCE::productEntityToProductDto);
    }

    private List<SortDto> jsonStringToSortDto(String jsonString) {
        try {
            ObjectMapper obj = new ObjectMapper();
            return obj.readValue(jsonString, new TypeReference<List<SortDto>>() {});
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}
