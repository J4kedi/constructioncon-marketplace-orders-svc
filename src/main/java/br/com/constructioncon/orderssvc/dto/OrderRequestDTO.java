
package br.com.constructioncon.orderssvc.dto;

import java.util.List;

public record OrderRequestDTO(
    String customerId,
    List<OrderItemDTO> items
) {}
