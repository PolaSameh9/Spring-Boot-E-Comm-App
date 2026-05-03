package com.example.SpringEcom.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String productName;
    private String productDesc;
    private String productBrand;
    private BigDecimal productPrice;
    private Date productDate;
    private String categoryName;
    // private byte[] imageData;

}
