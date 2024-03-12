package com.smartera.orderservice.service;

import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.repository.OrderRepository;
import com.smartera.orderservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yilmazsahin
 * @since 2/28/2024
 */
@Service
public class ProductService {
    private ProductRepository productRepository;
    private final OrderRepository orderRepository;


    public ProductService(ProductRepository productRepository,
                          OrderRepository orderRepository) {
        this.productRepository = productRepository;

        this.orderRepository = orderRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Product product) {
        if (product == null || product.getId() == null) {
            System.err.println("Customer is Nulll");
            return null;
        }
        var productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            var existingProduct = productOptional.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        }
        return null;

    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
