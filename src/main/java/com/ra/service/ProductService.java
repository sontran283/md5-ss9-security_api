package com.ra.service;

import com.ra.model.dto.request.ProductRequestDTO;
import com.ra.model.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAll();
    ProductResponseDTO save(ProductRequestDTO product);
}
