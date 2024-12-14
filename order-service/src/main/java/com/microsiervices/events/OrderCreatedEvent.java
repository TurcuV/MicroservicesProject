package com.microsiervices.events;

import com.microsiervices.order_service.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private UUID orderId;
    private List<OrderItemDto> orderItems;
}