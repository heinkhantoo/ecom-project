package project_oodd.ecom.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project_oodd.ecom.dto.OrderDTO;
import project_oodd.ecom.dto.OrderItemDTO;
import project_oodd.ecom.model.Cart;
import project_oodd.ecom.model.CartItem;
import project_oodd.ecom.model.Order;
import project_oodd.ecom.model.OrderItem;
import project_oodd.ecom.model.User;
import project_oodd.ecom.repository.CartItemRepository;
import project_oodd.ecom.repository.CartRepository;
import project_oodd.ecom.repository.OrderItemRepository;
import project_oodd.ecom.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private CartRepository cartRepository;

	public OrderDTO checkout(User user) {
        Cart cart = cartService.getActiveCart(user);
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot checkout");
        }

        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("RECEIVED");
        order.setTotal(cartItems.stream().mapToDouble(CartItem::getLineTotal).sum());
        order = orderRepository.save(order);

        // Convert CartItems -> OrderItems
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem ci : cartItems) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getProduct().getPrice());
            orderItemRepository.save(oi);
            orderItems.add(oi);
        }

        // Mark cart inactive
        cart.setActive(false);
        cartRepository.save(cart);

        // Map to DTO
        List<OrderItemDTO> itemDTOs = orderItems.stream()
                .map(oi -> new OrderItemDTO(
                        oi.getProduct().getProduct().getProductName(),
                        oi.getQuantity(),
                        oi.getPrice(),
                        oi.getLineTotal()
                ))
                .toList();

        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotal(),
                itemDTOs
        );
    }

    public List<OrderDTO> getOrders(User user) {
        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream().map(order -> {
            List<OrderItem> items = orderItemRepository.findByOrder(order);
            List<OrderItemDTO> itemDTOs = items.stream().map(oi -> new OrderItemDTO(
                    oi.getProduct().getProduct().getProductName(),
                    oi.getQuantity(),
                    oi.getPrice(),
                    oi.getLineTotal()
            )).toList();

            return new OrderDTO(
                    order.getId(),
                    order.getOrderDate(),
                    order.getStatus(),
                    order.getTotal(),
                    itemDTOs
            );
        }).toList();
    }
}
