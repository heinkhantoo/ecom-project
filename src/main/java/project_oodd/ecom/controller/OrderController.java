package project_oodd.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project_oodd.ecom.dto.OrderDTO;
import project_oodd.ecom.model.User;
import project_oodd.ecom.service.OrderService;
import project_oodd.ecom.util.ApiResponse;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<OrderDTO>> checkout(@AuthenticationPrincipal User user) {
        OrderDTO orderDTO = orderService.checkout(user);
        ApiResponse<OrderDTO> response = new ApiResponse<>("success", orderDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrders(@AuthenticationPrincipal User user) {
        List<OrderDTO> orders = orderService.getOrders(user);
        return ResponseEntity.ok(new ApiResponse<>("success", orders));
    }
}
