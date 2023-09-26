package com.migi.migi_project.service.user;

import com.migi.migi_project.model.dto.OrderProductDTO;
import com.migi.migi_project.model.dto.OrdersDTO;
import com.migi.migi_project.model.response.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrdersDTO findOrderByIdUser(Integer idUser);
    List<OrderProductDTO> findOrderProductByIdOrder(Integer idOrder);
    OrdersDTO findOrderById(Integer id);
    OrdersDTO addOrder(OrdersDTO ordersDTO);
    OrdersDTO updateOrder(OrdersDTO ordersDTO);
    Boolean deleteOrderById(Integer id);
    OrderProductDTO addOrderProduct(OrderProductDTO orderProductDTO);
    OrderProductDTO findOrderProductByIdOrderAndIdProduct(Integer idOrder, Integer idProduct);
    OrderProductDTO updateOrderProduct(OrderProductDTO orderProductDTO);
    Boolean deleteOrderProductById(Integer id);
    OrderProductDTO findOrderProductById(Integer id);
    List<OrdersDTO> getListOrderHistory(Integer idOrder);

    Boolean deleteOrderAndRelate(Integer id);

    List<OrderItem> getOrderProductByIdOrder(Integer id);
}
