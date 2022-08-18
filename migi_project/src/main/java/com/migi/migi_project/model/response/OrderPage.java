package com.migi.migi_project.model.response;

import com.migi.migi_project.entity.OrderProduct;
import com.migi.migi_project.entity.Product;
import com.migi.migi_project.model.dto.OrderProductDTO;
import com.migi.migi_project.model.dto.OrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPage {
    private OrdersDTO ordersDTO;
    private List<OrderProductDTO> orderProducts;
}
