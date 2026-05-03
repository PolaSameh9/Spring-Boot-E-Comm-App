package com.example.SpringEcom.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.model.dto.FilterDto;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {

    public static Specification<Product> getSpecification(FilterDto filterDto) {
                return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getCategoryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"),filterDto.getCategoryId()));
            }

            if (filterDto.getProductPrice() != null) {
                predicates.add(criteriaBuilder.equal(root.get("productPrice"),filterDto.getProductPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}


