package com.migi.migi_project.model.response;

import com.migi.migi_project.model.dto.OrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class AddProductToOrderResponse {
    private OrdersDTO ordersDTO;
    private String msg;

}
