package com.microservice.ecommerce.product;
import com.microservice.ecommerce.exception.ProductNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public String createProduct(ProductRequest request) {
        Product product = productMapper.convertToEntity(request);
        return repository.save(product).getName();
    }

    public ProductResponse getProduct(Long productId) {
        Product product = repository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId));
        return productMapper.ConvertToResponse(product);
    }


    public List<ProductResponse> getProducts() {
        List<Product> products = repository.findAll();
        return products
                .stream()
                .map(productMapper::ConvertToResponse)
                .collect(Collectors.toList());
    }
}
