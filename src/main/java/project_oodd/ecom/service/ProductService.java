package project_oodd.ecom.service;

import project_oodd.ecom.dto.ProductReqDTO;
import project_oodd.ecom.dto.ProductResDTO;
import project_oodd.ecom.dto.VariantDTO;

import java.util.List;

public interface ProductService {

	List<ProductResDTO> getProducts();

	ProductResDTO getProductById(String id);

	ProductResDTO createProduct(ProductReqDTO product);

	ProductResDTO updateProduct(String id, ProductReqDTO product);

	void deleteProduct(String id);

	List<VariantDTO> getVariants();

	VariantDTO getVariantById(String id);

	VariantDTO createVariant(String productCode, VariantDTO variant);

	VariantDTO updateVariant(String id, String productCode, VariantDTO variant);

	void deleteVariant(String id);
}
