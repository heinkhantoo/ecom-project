package project_oodd.ecom.service;

import project_oodd.ecom.dto.ProductReqDTO;
import project_oodd.ecom.dto.ProductResDTO;
import project_oodd.ecom.dto.VariantReqDTO;
import project_oodd.ecom.dto.VariantResDTO;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	List<ProductResDTO> getProducts();

	ProductResDTO getProductById(UUID id);

	ProductResDTO createProduct(ProductReqDTO product, MultipartFile imageFile, List<MultipartFile> vImageFiles);

	ProductResDTO updateProduct(UUID id, ProductReqDTO product, MultipartFile imageFile);

	void deleteProduct(UUID id);

	List<VariantResDTO> getVariants();

	VariantResDTO getVariantById(UUID id);

	VariantResDTO createVariant(UUID pid, VariantReqDTO variant, MultipartFile imageFile);

	VariantResDTO updateVariant(UUID pid, UUID id, VariantReqDTO variant, MultipartFile imageFile);

	void deleteVariant(UUID id);
}
