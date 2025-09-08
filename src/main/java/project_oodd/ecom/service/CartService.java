package project_oodd.ecom.service;

import java.util.List;
import java.util.UUID;

import project_oodd.ecom.dto.CartItemDTO;
import project_oodd.ecom.model.Cart;
import project_oodd.ecom.model.CartItem;
import project_oodd.ecom.model.User;

public interface CartService {

	public Cart getActiveCart(User user);

	public Cart addToCart(User user, UUID id, int quantity);

	public Cart updateCartItemQuantity(UUID cartItemId, int newQuantity);

	public void removeItem(UUID cartItemId);

	public void discardCart(User user);

	public double calculateTotal(Cart cart);
	
	public List<CartItemDTO> mapToDTOs(List<CartItem> items);
}
