package com.ra.service;

import com.ra.model.dto.request.ProductRequestDTO;
import com.ra.model.dto.response.ProductResponseDTO;
import com.ra.model.entity.Product;
import com.ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadService uploadService;

    @Override
    public List<ProductResponseDTO> getAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(ProductResponseDTO::new).toList();
    }

    @Override
    public ProductResponseDTO save(ProductRequestDTO product) {
        Product productNew = new Product();
        productNew.setName(product.getName());
        productNew.setPrice(product.getPrice());
        // upload file
        String fileName = uploadService.uploadImage(product.getFile());
        productNew.setImage(fileName);
        // lưu
        productRepository.save(productNew);
        // convert Product Entity => ProductResponseDTO
        return new ProductResponseDTO(productNew);
    }
}
