package project_oodd.ecom.service;

import project_oodd.ecom.dto.ProductDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.Category;
import project_oodd.ecom.model.Color;
import project_oodd.ecom.model.Product;
import project_oodd.ecom.model.Size;
import project_oodd.ecom.model.SubCategory;
import project_oodd.ecom.repository.CategoryRepository;
import project_oodd.ecom.repository.ColorRepository;
import project_oodd.ecom.repository.ProductRepository;
import project_oodd.ecom.repository.SizeRepository;
import project_oodd.ecom.repository.SubCategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	public List<ProductDTO> getProducts() {
		return convertToDTO(productRepository.findAll());
	}

	public ProductDTO getProductById(String id) {
		return convertToDTO(productRepository.findByProductCodeIgnoreCase(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404)));
	}

	public ProductDTO createProduct(ProductDTO dto) {
		Product product = new Product();
		product.setProductCode(dto.getProductCode());
		product.setProductName(dto.getProductName());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setImg(dto.getImg());

		if (dto.getColors() != null) {
			Set<Color> colors = dto.getColors().stream()
					.map(colorCode -> colorRepository.findByColorCodeIgnoreCase(colorCode)
							.orElseThrow(() -> new AppException("Please, create this type of color first!", 404)))
					.collect(Collectors.toSet());
			product.setColors(colors);
		}

		if (dto.getSizes() != null) {
			Set<Size> sizes = dto.getSizes().stream()
					.map(value -> sizeRepository.findByValueIgnoreCase(value).orElseThrow(
							() -> new AppException("Size invalid! Please create this type of size first!", 404)))
					.collect(Collectors.toSet());
			product.setSizes(sizes);
		}

		if (dto.getCategory() != null) {
			Category cat = categoryRepository.findByCategoryCodeIgnoreCase(dto.getCategory())
					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404));
			product.setCategory(cat);
		}

		if (dto.getSubCategory() != null) {
			SubCategory subCat = subCategoryRepository.findBySubCategoryCodeIgnoreCase(dto.getSubCategory())
					.orElseThrow(() -> new AppException("Please, create this type of sub-category first!", 404));
			product.setSubCategory(subCat);
		}

		productRepository.save(product);
		return dto;
	}

//	private String generateColorCode(String color) {
//
//		String trimmed = color.trim().toUpperCase();
//		int length = color.length();
//
//		String part1 = length >= 2 ? trimmed.substring(0, 2) : trimmed;
//		String res = part1 + trimmed.substring(length - 1) + "001";
//
//		return res;
//	}

	public ProductDTO updateProduct(String id, ProductDTO dto) {

		Product p = productRepository.findByProductCodeIgnoreCase(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404));

		if (dto.getProductCode() != null)
			p.setProductCode(dto.getProductCode());
		if (dto.getProductName() != null)
			p.setProductName(dto.getProductName());
		if (dto.getPrice() != null)
			p.setPrice(dto.getPrice());
		if (dto.getStock() != null)
			p.setStock(dto.getStock());
		if (dto.getImg() != null)
			p.setImg(dto.getImg());
		if (dto.getColors() != null)
			p.setColors(dto.getColors().stream()
					.map(code -> colorRepository.findByColorCodeIgnoreCase(code)
							.orElseThrow(() -> new AppException("Please, create this type of color first!", 404)))
					.collect(Collectors.toSet()));

		if (dto.getSizes() != null)
			p.setSizes(dto.getSizes().stream()
					.map(value -> sizeRepository.findByValueIgnoreCase(value).orElseThrow(
							() -> new AppException("Size invalid! Please create this type of size first!", 404)))
					.collect(Collectors.toSet()));

		if (dto.getCategory() != null)
			p.setCategory(categoryRepository.findByCategoryCodeIgnoreCase(dto.getCategory())
					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404)));
		if (dto.getSubCategory() != null)
			p.setSubCategory(subCategoryRepository.findBySubCategoryCodeIgnoreCase(dto.getSubCategory())
					.orElseThrow(() -> new AppException("Please, create this type of sub-category first!", 404)));

		return convertToDTO(productRepository.save(p));
	}

	public void deleteProduct(String id) {
		Product product = productRepository.findByProductCodeIgnoreCase(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404));
		productRepository.delete(product);
	}

	public ProductDTO convertToDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setProductCode(product.getProductCode());
		dto.setProductName(product.getProductName());
		if (product.getPrice() != null) {
			dto.setPrice(product.getPrice());
		}
		if (product.getStock() != null) {
			dto.setStock(product.getStock());
		}
		if (product.getImg() != null) {
			dto.setImg(product.getImg());
		}
		if (product.getCategory() != null) {
			dto.setCategory(product.getCategory().getCategoryName());
		}
		if (product.getSubCategory() != null) {
			dto.setSubCategory(product.getSubCategory().getSubCategoryName());
		}
		if (product.getColors() != null) {
			dto.setColors(product.getColors().stream().map(Color::getColorDescription).collect(Collectors.toSet()));
		}
		if (product.getSizes() != null) {
			dto.setSizes(product.getSizes().stream().map(Size::getValue).collect(Collectors.toSet()));
		}

		return dto;
	}

	public List<ProductDTO> convertToDTO(List<Product> products) {
		if (products == null || products.isEmpty()) {
			return Collections.emptyList();
		}

		return products.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
