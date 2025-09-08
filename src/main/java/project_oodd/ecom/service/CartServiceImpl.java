package project_oodd.ecom.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project_oodd.ecom.dto.CartItemDTO;
import project_oodd.ecom.model.Cart;
import project_oodd.ecom.model.CartItem;
import project_oodd.ecom.model.User;
import project_oodd.ecom.model.Variant;
import project_oodd.ecom.repository.CartItemRepository;
import project_oodd.ecom.repository.CartRepository;
import project_oodd.ecom.repository.VariantRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private VariantRepository productRepository;

	public Cart getActiveCart(User user) {
		return cartRepository.findByUserAndActiveTrue(user).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUser(user);
			newCart.setActive(true);
			return cartRepository.save(newCart);
		});
	}

	public Cart addToCart(User user, UUID id, int quantity) {
		Cart cart = getActiveCart(user);
		Variant product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);

		if (existingItem.isPresent()) {
			CartItem item = existingItem.get();
			item.setQuantity(item.getQuantity() + quantity);
			cartItemRepository.save(item);
		} else {
			CartItem newItem = new CartItem();
			newItem.setCart(cart);
			newItem.setProduct(product);
			newItem.setQuantity(quantity);
			cartItemRepository.save(newItem);
		}

		return cart;
	}

	public Cart updateCartItemQuantity(UUID cartItemId, int newQuantity) {
		CartItem item = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new RuntimeException("Cart item not found"));
		if (newQuantity <= 0) {
			cartItemRepository.delete(item);
		} else {
			item.setQuantity(newQuantity);
			cartItemRepository.save(item);
		}
		return item.getCart();
	}

	public void removeItem(UUID cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}

	public void discardCart(User user) {
		Cart cart = getActiveCart(user);
		cartItemRepository.deleteAll(cartItemRepository.findByCart(cart));
		cart.setActive(false);
		cartRepository.save(cart);
	}

	public double calculateTotal(Cart cart) {
		return cartItemRepository.findByCart(cart).stream().mapToDouble(CartItem::getLineTotal).sum();
	}
	
    public List<CartItemDTO> mapToDTOs(List<CartItem> items) {
        return items.stream()
                .map(i -> new CartItemDTO(
                        i.getProduct().getProduct().getProductName(),
                        i.getProduct().getColor().getColorDescription(),
                        i.getProduct().getSize().getValue(),
                        i.getQuantity(),
                        i.getLineTotal()
                ))
                .toList();
    }
}
