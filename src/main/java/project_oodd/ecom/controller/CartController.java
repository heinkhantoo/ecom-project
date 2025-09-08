package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import project_oodd.ecom.dto.CartItemDTO;
import project_oodd.ecom.model.Cart;
import project_oodd.ecom.model.CartItem;
import project_oodd.ecom.model.User;
import project_oodd.ecom.repository.CartItemRepository;
import project_oodd.ecom.service.CartService;
import project_oodd.ecom.util.ApiResponse;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemRepository cartItemRepository;

	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Map<String, Object>>> addToCart(@AuthenticationPrincipal User user,
			@RequestParam UUID productId, @RequestParam int quantity) {
		Cart cart = cartService.addToCart(user, productId, quantity);

		Map<String, Object> data = Map.of("data", cart);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/update")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateQuantity(@RequestParam UUID cartItemId,
			@RequestParam int quantity) {
		Cart cart = cartService.updateCartItemQuantity(cartItemId, quantity);

		Map<String, Object> data = Map.of("data", cart);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove/{cartItemId}")
	public ResponseEntity<Void> removeItem(@PathVariable UUID cartItemId) {
		cartService.removeItem(cartItemId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/discard")
	public ResponseEntity<Void> discardCart(@AuthenticationPrincipal User user) {
		cartService.discardCart(user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> viewCart(@AuthenticationPrincipal User user) {
		Cart cart = cartService.getActiveCart(user);
		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
		double total = cartService.calculateTotal(cart);

		List<CartItemDTO> items = cartService.mapToDTOs(cartItems);

		Map<String, Object> data = Map.of("items", items, "total", total);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}
}
