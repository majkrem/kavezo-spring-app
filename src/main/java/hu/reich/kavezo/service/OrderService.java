package hu.reich.kavezo.service;

import hu.reich.kavezo.model.Order;
import hu.reich.kavezo.model.Product;
import hu.reich.kavezo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order saveOrder(String customerName, List<Product> products) {
        double total = products.stream().mapToDouble(Product::getPrice).sum();

        Order order = new Order();
        order.setCustomerName(customerName);
        order.setProducts(products);
        order.setTotalPrice(total);

        return orderRepository.save(order);
    }
}