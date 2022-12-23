package pl.limescode.gkcart.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.limescode.gkcart.dto.OrderDto;
import pl.limescode.gkcart.model.Order;
import pl.limescode.gkcart.cache.OrderCache;
import pl.limescode.gkcart.validator.OrderValidator;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderCache orderCache;
    private final OrderValidator orderValidator;

    public Order findOrderByProductId(Long productId) {
        return orderCache.findOrderByProductId(productId);
    }

    public List<Order> findAll() {
        return orderCache.findAll();
    }

    public Order addOrder(OrderDto dto) {
        orderValidator.validate(dto);
        Order order = Order.builder()
                .productId(dto.getProductId())
                .name(dto.getName())
                .price(dto.getPrice())
                .total(dto.getPrice())
                .amount(1)
                .added(Instant.now())
                .build();
        return orderCache.save(order);
    }

    public void addItem(Long productId) {
        orderCache.addItem(productId);
    }

    public void removeItem(Long productId) {
        orderCache.removeItem(productId);
    }

    public void deleteOrderByProductId(Long productId) {
        orderCache.deleteByProductId(productId);
    }
}
