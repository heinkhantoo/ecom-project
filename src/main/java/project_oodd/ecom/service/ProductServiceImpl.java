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
import project_oodd.ecom.util.FileStorageService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Autowired
	private FileStorageService fileStorageService;

	public List<ProductResDTO> getProducts() {
		return convertToDTO(productRepository.findAll());
	}

	public ProductResDTO getProductById(UUID id) {
		return convertToDTO(productRepository.findById(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404)));
	}

	public ProductResDTO createProduct(ProductReqDTO data, MultipartFile imageFile, List<MultipartFile> vImageFiles) {
		Product product = new Product();
		product.setProductName(data.getProductName());
		product.setPrice(data.getPrice());

		if (imageFile != null && !imageFile.isEmpty()) {
			String imageUrl = fileStorageService.storeFile(imageFile);
			data.setImg(imageUrl);
		}
		product.setImg(data.getImg());

		if (data.getSubCategory() != null) {
			SubCategory subCat = subCategoryRepository.findById(data.getSubCategory())
					.orElseThrow(() -> new AppException("Please, create this type of sub-category first!", 404));
			product.setSubCategory(subCat);
		}

		Product savedProduct = productRepository.save(product);

		List<VariantReqDTO> variantReqs = data.getVariants();

		if (variantReqs != null && vImageFiles != null) {
			for (VariantReqDTO variant : variantReqs) {
				String imgName = variant.getImageName();
				if (imgName != null) {
					MultipartFile file = vImageFiles.stream().filter(
							f -> f.getOriginalFilename() != null && f.getOriginalFilename().equalsIgnoreCase(imgName))
							.findFirst().orElse(null);

					if (file != null && !file.isEmpty()) {
						String url = fileStorageService.storeFile(file);
						variant.setImageUrl(url);
					}
				}
			}
		}

		List<Variant> variants = variantReqs.stream().map(variant -> {
			Variant v = new Variant();
			Color color = colorRepository.findById(variant.getColor())
					.orElseThrow(() -> new AppException("Please, create this type of color first!", 404));
			v.setColor(color);

			Size size = sizeRepository.findById(variant.getSize())
					.orElseThrow(() -> new AppException("Please, create this type of size first!", 404));
			v.setSize(size);
			v.setProduct(savedProduct);
			v.setStock(variant.getStock());
			v.setImageUrl(variant.getImageUrl());
			return v;
		}).toList();

		variantRepository.saveAll(variants);

		return convertToDTO(productRepository.save(product));
	}

	public ProductResDTO updateProduct(UUID id, ProductReqDTO data, MultipartFile imageFile) {

		Product p = productRepository.findById(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404));

		if (data != null) {

//		if (data.getProductCode() != null)
//			p.setProductCode(data.getProductCode());
			if (data.getProductName() != null)
				p.setProductName(data.getProductName());
			if (data.getPrice() != null)
				p.setPrice(data.getPrice());

//		if (data.getCategory() != null)
//			p.setCategory(categoryRepository.findByCategoryCodeIgnoreCase(data.getCategory())
//					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404)));
			if (data.getSubCategory() != null)
				p.setSubCategory(subCategoryRepository.findById(data.getSubCategory())
						.orElseThrow(() -> new AppException("Please, create this type of sub-category first!", 404)));

		}
		if (imageFile != null && !imageFile.isEmpty()) {
			if (p.getImg() != null) {
				fileStorageService.deleteFile(p.getImg());
			}

			String imageUrl = fileStorageService.storeFile(imageFile);
			p.setImg(imageUrl);
		}
		return convertToDTO(productRepository.save(p));
	}

	public void deleteProduct(UUID id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404));
		if (!product.getImg().isBlank()) {
			fileStorageService.deleteFile(product.getImg());
		}
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

	public VariantResDTO createVariant(UUID pid, VariantReqDTO data, MultipartFile imageFile) {
		Variant variant = new Variant();
		variant.setStock(data.getStock());
//		variant.setSku(data.getSku());

		if (imageFile != null && !imageFile.isEmpty()) {
			String imageUrl = fileStorageService.storeFile(imageFile);
			data.setImageUrl(imageUrl);
		}

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

	public VariantResDTO updateVariant(UUID pid, UUID id, VariantReqDTO data, MultipartFile imageFile) {

		Variant v = variantRepository.findById(id)
				.orElseThrow(() -> new AppException("Variant not found with this Id", 404));
		if (data != null) {
//		if (data.getSku() != null)
//			v.setSku(data.getSku());
			if (data.getStock() != null)
				v.setStock(data.getStock());
			if (data.getProduct() != null)
				v.setProduct(productRepository.findById(data.getProduct())
						.orElseThrow(() -> new AppException("The product no longer exists!", 404)));
			if (data.getColor() != null)
				v.setColor(colorRepository.findById(data.getColor())
						.orElseThrow(() -> new AppException("Please, create this type of color first!", 404)));
			if (data.getSize() != null)
				v.setSize(sizeRepository.findById(data.getSize())
						.orElseThrow(() -> new AppException("Please, create this type of size first!", 404)));
		}
		if (imageFile != null && !imageFile.isEmpty()) {
			if (v.getImageUrl() != null) {
				fileStorageService.deleteFile(v.getImageUrl());
			}

			String imageUrl = fileStorageService.storeFile(imageFile);
			v.setImageUrl(imageUrl);
		}

		return convertToDTO(variantRepository.save(v));
	}

	public void deleteVariant(UUID id) {
		Variant variant = variantRepository.findById(id)
				.orElseThrow(() -> new AppException("Variant not found with this Id", 404));
		if (!variant.getImageUrl().isBlank()) {
			fileStorageService.deleteFile(variant.getImageUrl());
		}
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
