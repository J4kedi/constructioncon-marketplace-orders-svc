package br.com.constructioncon.orderssvc.service;

import br.com.constructioncon.orderssvc.dto.OrderItemDTO;
import br.com.constructioncon.orderssvc.dto.OrderRequestDTO;
import br.com.constructioncon.orderssvc.dto.OrderResponseDTO;
import br.com.constructioncon.orderssvc.model.Order;
import br.com.constructioncon.orderssvc.model.OrderItem;
import br.com.constructioncon.orderssvc.model.OrderStatus;
import br.com.constructioncon.orderssvc.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = toEntity(orderRequestDTO);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        BigDecimal total = order.getItems().stream()
            .map(item -> item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);
        return toResponseDTO(savedOrder);
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return toResponseDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
            .map(item -> new OrderItemDTO(
                item.getProductId(),
                item.getQuantity(),
                item.getUnitPrice()))
            .collect(Collectors.toList());

        return new OrderResponseDTO(
            order.getId(),
            order.getCustomerId(),
            order.getOrderDate(),
            order.getStatus(),
            itemDTOs,
            order.getTotalAmount());
    }

    private Order toEntity(OrderRequestDTO dto) {
        Order order = Order.builder()
            .customerId(dto.customerId())
            .build();

        List<OrderItem> items = dto.items().stream()
            .map(itemDto -> OrderItem.builder()
                .productId(itemDto.productId())
                .quantity(itemDto.quantity())
                .unitPrice(itemDto.unitPrice())
                .order(order)
                .build())
            .collect(Collectors.toList());

        order.setItems(items);
        return order;
    }
}
