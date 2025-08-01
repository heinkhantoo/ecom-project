package project_oodd.ecom.service;

import project_oodd.ecom.dto.ProductDTO;

import java.util.List;

public interface ProductService {
	
	List<ProductDTO> getProducts();
    ProductDTO getProductById(String id);
    ProductDTO createProduct(ProductDTO product);
    ProductDTO updateProduct(String id, ProductDTO product);
    void deleteProduct(String id);
}
