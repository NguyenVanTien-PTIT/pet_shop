package com.migi.migi_project.model.response;

import com.migi.migi_project.model.dto.OrdersDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadOrderMobileResponse implements Serializable {
    private OrdersDTO ordersDTO;
    private List<OrderItem> orderItems;
}
