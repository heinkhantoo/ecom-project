package project_oodd.ecom.service;

import project_oodd.ecom.dto.ProductReqDTO;
import project_oodd.ecom.dto.ProductResDTO;
import project_oodd.ecom.dto.VariantReqDTO;
import project_oodd.ecom.dto.VariantResDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.Color;
import project_oodd.ecom.model.Product;
import project_oodd.ecom.model.Size;
import project_oodd.ecom.model.SubCategory;
import project_oodd.ecom.model.Variant;
import project_oodd.ecom.repository.ColorRepository;
import project_oodd.ecom.repository.ProductRepository;
import project_oodd.ecom.repository.SizeRepository;
import project_oodd.ecom.repository.SubCategoryRepository;
import project_oodd.ecom.repository.VariantRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private VariantRepository variantRepository;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private SizeRepository sizeRepository;

	public List<ProductResDTO> getProducts() {
		return convertToDTO(productRepository.findAll());
	}

	public ProductResDTO getProductById(UUID id) {
		return convertToDTO(productRepository.findById(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404)));
	}

	public ProductResDTO createProduct(ProductReqDTO data) {
		Product product = new Product();
		product.setProductName(data.getProductName());
		product.setImg(data.getImg());

//		if (data.getCategory() != null) {
//			Category cat = categoryRepository.findByCategoryCodeIgnoreCase(data.getCategory())
//					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404));
//			product.setCategory(cat);
//		}

		if (data.getSubCategory() != null) {
			SubCategory subCat = subCategoryRepository.findById(data.getSubCategory())
					.orElseThrow(() -> new AppException("Please, create this type of sub-category first!", 404));
			product.setSubCategory(subCat);
		}

		Product savedProduct = productRepository.save(product);

		if (data.getVariants() != null) {
			List<Variant> variants = data.getVariants().stream().map(variant -> {
				Variant v = new Variant();
				Color color = colorRepository.findById(variant.getColor())
						.orElseThrow(() -> new AppException("Please, create this type of color first!", 404));
				v.setColor(color);

				Size size = sizeRepository.findById(variant.getSize())
						.orElseThrow(() -> new AppException("Please, create this type of size first!", 404));
				v.setSize(size);
				v.setProduct(savedProduct);
				v.setStock(variant.getStock());
				return v;
			}).toList();

			variantRepository.saveAll(variants);
		}

		return convertToDTO(productRepository.save(product));
	}

	public ProductResDTO updateProduct(UUID id, ProductReqDTO data) {

		Product p = productRepository.findById(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404));

//		if (data.getProductCode() != null)
//			p.setProductCode(data.getProductCode());
		if (data.getProductName() != null)
			p.setProductName(data.getProductName());
		if (data.getPrice() != null)
			p.setPrice(data.getPrice());
		if (data.getImg() != null)
			p.setImg(data.getImg());
//		if (data.getCategory() != null)
//			p.setCategory(categoryRepository.findByCategoryCodeIgnoreCase(data.getCategory())
//					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404)));
		if (data.getSubCategory() != null)
			p.setSubCategory(subCategoryRepository.findById(data.getSubCategory())
					.orElseThrow(() -> new AppException("Please, create this type of sub-category first!", 404)));

		return convertToDTO(productRepository.save(p));
	}

	public void deleteProduct(UUID id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404));
		productRepository.delete(product);
	}

	public ProductResDTO convertToDTO(Product product) {
		ProductResDTO dto = new ProductResDTO();
//		dto.setProductCode(product.getProductCode());
		dto.setPid(product.getPid());
		dto.setProductName(product.getProductName());
		if (product.getPrice() != null) {
			dto.setPrice(product.getPrice());
		}
		if (product.getImg() != null) {
			dto.setImg(product.getImg());
		}
//		if (product.getCategory() != null) {
//			dto.setCategory(product.getCategory().getCategoryName());
//		}
		if (product.getSubCategory() != null) {
			dto.setSubCategory(product.getSubCategory().getSubCategoryName());
		}
		dto.setCreatedDate(product.getCreatedDate());

		return dto;
	}

	public List<ProductResDTO> convertToDTO(List<Product> products) {
		if (products == null || products.isEmpty()) {
			return Collections.emptyList();
		}

		return products.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public List<VariantResDTO> getVariants() {
		return convertToVDTO(variantRepository.findAll());
	}

	public VariantResDTO getVariantById(UUID id) {
		return convertToDTO(variantRepository.findById(id)
				.orElseThrow(() -> new AppException("Variant not found with this Id", 404)));
	}

	public VariantResDTO createVariant(UUID pid, VariantReqDTO data) {
		Variant variant = new Variant();
		variant.setStock(data.getStock());
//		variant.setSku(data.getSku());
		variant.setImageUrl(data.getImageUrl());

		Product product = productRepository.findById(pid)
				.orElseThrow(() -> new AppException("Please, create this type of product first!", 404));
		variant.setProduct(product);

		if (data.getColor() != null) {
			Color color = colorRepository.findById(data.getColor())
					.orElseThrow(() -> new AppException("Please, create this type of color first!", 404));
			variant.setColor(color);
		}

		if (data.getSize() != null) {
			Size size = sizeRepository.findById(data.getSize())
					.orElseThrow(() -> new AppException("Please, create this type of size first!", 404));
			variant.setSize(size);
		}

		return convertToDTO(variantRepository.save(variant));
	}

	public VariantResDTO updateVariant(UUID id, VariantReqDTO data) {

		Variant v = variantRepository.findById(id)
				.orElseThrow(() -> new AppException("Variant not found with this Id", 404));

//		if (data.getSku() != null)
//			v.setSku(data.getSku());
		if (data.getStock() != null)
			v.setStock(data.getStock());
		if (data.getImageUrl() != null)
			v.setImageUrl(data.getImageUrl());
		if (data.getProduct() != null)
			v.setProduct(productRepository.findById(data.getProduct())
					.orElseThrow(() -> new AppException("The product no longer exists!", 404)));
		if (data.getColor() != null)
			v.setColor(colorRepository.findById(data.getColor())
					.orElseThrow(() -> new AppException("Please, create this type of color first!", 404)));
		if (data.getSize() != null)
			v.setSize(sizeRepository.findById(data.getSize())
					.orElseThrow(() -> new AppException("Please, create this type of size first!", 404)));

		return convertToDTO(variantRepository.save(v));
	}

	public void deleteVariant(UUID id) {
		Variant variant = variantRepository.findById(id)
				.orElseThrow(() -> new AppException("Variant not found with this Id", 404));
		variantRepository.delete(variant);
	}

	public VariantResDTO convertToDTO(Variant variant) {
		VariantResDTO dto = new VariantResDTO();
		dto.setVid(variant.getVid());
		dto.setProduct(variant.getProduct().getProductName());
		dto.setSize(variant.getSize().getValue());
		dto.setColor(variant.getColor().getColorDescription());
//		dto.setSku(variant.getSku());
		dto.setStock(variant.getStock());
		dto.setImageUrl(variant.getImageUrl());
		return dto;
	}

	public List<VariantResDTO> convertToVDTO(List<Variant> variants) {
		if (variants == null || variants.isEmpty()) {
			return Collections.emptyList();
		}

		return variants.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
