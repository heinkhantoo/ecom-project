package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.OrderDTO;
import project_oodd.ecom.model.User;

public interface OrderService {
	public OrderDTO checkout(User user);
	public List<OrderDTO> getOrders(User user);
}
