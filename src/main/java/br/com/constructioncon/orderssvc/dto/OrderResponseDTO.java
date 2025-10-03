
package br.com.constructioncon.orderssvc.dto;

import br.com.constructioncon.orderssvc.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
    Long id,
    String customerId,
    LocalDateTime orderDate,
    OrderStatus status,
    List<OrderItemDTO> items,
    BigDecimal totalAmount
) {}
