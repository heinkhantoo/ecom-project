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

	public ProductDTO createProduct(ProductDTO data) {
		Product product = new Product();
		product.setProductCode(data.getProductCode());
		product.setProductName(data.getProductName());
		product.setPrice(data.getPrice());
		product.setStock(data.getStock());
		product.setImg(data.getImg());

		if (data.getColors() != null) {
			Set<Color> colors = data.getColors().stream()
					.map(color -> colorRepository.findByColorCodeIgnoreCase(color)
							.orElseThrow(() -> new AppException("Please, create this type of color first!", 404)))
					.collect(Collectors.toSet());
			product.setColors(colors);
		}

		if (data.getSizes() != null) {
			Set<Size> sizes = data.getSizes().stream()
					.map(size -> sizeRepository.findByValueIgnoreCase(size).orElseThrow(
							() -> new AppException("Size invalid! Please create this type of size first!", 404)))
					.collect(Collectors.toSet());
			product.setSizes(sizes);
		}

		if (data.getCategory() != null) {
			Category cat = categoryRepository.findByCategoryCodeIgnoreCase(data.getCategory())
					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404));
			product.setCategory(cat);
		}

		if (data.getSubCategory() != null) {
			SubCategory subCat = subCategoryRepository.findBySubCategoryCodeIgnoreCase(data.getSubCategory())
					.orElseThrow(() -> new AppException("Please, create this type of sub-category first!", 404));
			product.setSubCategory(subCat);
		}

		
		return convertToDTO(productRepository.save(product));
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

	public ProductDTO updateProduct(String id, ProductDTO data) {

		Product p = productRepository.findByProductCodeIgnoreCase(id)
				.orElseThrow(() -> new AppException("Product not found with this Id", 404));

		if (data.getProductCode() != null)
			p.setProductCode(data.getProductCode());
		if (data.getProductName() != null)
			p.setProductName(data.getProductName());
		if (data.getPrice() != null)
			p.setPrice(data.getPrice());
		if (data.getStock() != null)
			p.setStock(data.getStock());
		if (data.getImg() != null)
			p.setImg(data.getImg());
		if (data.getColors() != null)
			p.setColors(data.getColors().stream()
					.map(color -> colorRepository.findByColorCodeIgnoreCase(color)
							.orElseThrow(() -> new AppException("Please, create this type of color first!", 404)))
					.collect(Collectors.toSet()));

		if (data.getSizes() != null)
			p.setSizes(data.getSizes().stream()
					.map(size -> sizeRepository.findByValueIgnoreCase(size).orElseThrow(
							() -> new AppException("Size invalid! Please create this type of size first!", 404)))
					.collect(Collectors.toSet()));

		if (data.getCategory() != null)
			p.setCategory(categoryRepository.findByCategoryCodeIgnoreCase(data.getCategory())
					.orElseThrow(() -> new AppException("Please, create this type of category first!", 404)));
		if (data.getSubCategory() != null)
			p.setSubCategory(subCategoryRepository.findBySubCategoryCodeIgnoreCase(data.getSubCategory())
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
