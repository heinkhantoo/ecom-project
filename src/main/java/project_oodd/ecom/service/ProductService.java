package project_oodd.ecom.service;

import project_oodd.ecom.dto.ProductReqDTO;
import project_oodd.ecom.dto.ProductResDTO;
import project_oodd.ecom.dto.VariantReqDTO;
import project_oodd.ecom.dto.VariantResDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

	List<ProductResDTO> getProducts();

	ProductResDTO getProductById(UUID id);

	ProductResDTO createProduct(ProductReqDTO product);

	ProductResDTO updateProduct(UUID id, ProductReqDTO product);

	void deleteProduct(UUID id);

	List<VariantResDTO> getVariants();

	VariantResDTO getVariantById(UUID id);

	VariantResDTO createVariant(UUID productCode, VariantReqDTO variant);

	VariantResDTO updateVariant(UUID id,VariantReqDTO variant);

	void deleteVariant(UUID id);
}
