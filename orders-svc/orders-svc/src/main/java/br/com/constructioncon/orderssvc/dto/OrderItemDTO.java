package br.com.constructioncon.orderssvc.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
    String productId,
    Integer quantity,
    BigDecimal unitPrice
) {}