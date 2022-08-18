package com.migi.migi_project.service.admin.impl;

import com.migi.migi_project.entity.OrderProduct;
import com.migi.migi_project.entity.Orders;
import com.migi.migi_project.entity.User;
import com.migi.migi_project.model.dto.OrderProductDTO;
import com.migi.migi_project.model.dto.OrdersDTO;
import com.migi.migi_project.model.dto.Revenue;
import com.migi.migi_project.model.mapper.OrderProductMapper;
import com.migi.migi_project.model.mapper.OrdersMapper;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.repository.user.OrderProductRepository;
import com.migi.migi_project.repository.user.OrderRepository;
import com.migi.migi_project.repository.user.UserRepository;
import com.migi.migi_project.service.admin.ManageOrderService;
import com.migi.migi_project.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManageOrderServiceImpl implements ManageOrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserRepository userRepository;

    @Override
    public List<OrdersDTO> findOrderByStatus(Integer status, Pageable pageable) {
        List<Orders> ordersList = orderRepository.findByStatus(status, pageable);
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        for(Orders o : ordersList){
            ordersDTOS.add(OrdersMapper.toOrdersDTO(o));
        }
        return ordersDTOS;
    }

    @Override
    public Long countOdersByStatus(Integer status) {
        return orderRepository.countOrdersByStatus(status);
    }

    @Override
    public ResponseNormal confirmOrder(Integer id) {
        Orders order = orderRepository.findById(id).get();
        //Duyệt đơn hàng
        if(order.getStatus() == 1){
            order.setStatus(2);
            orderRepository.save(order);
            return new ResponseNormal("Đã chuyển sang trạng thái giao hàng!", HttpStatus.OK);
        }else if(order.getStatus() == 2){
            order.setStatus(3);
            orderRepository.save(order);
            return new ResponseNormal("Đơn hàng chuyển sang trạng thái đã giao hàng!", HttpStatus.OK);
        }
        else{
            return new ResponseNormal("Đơn hàng đang trong trạng thái đặt hàng, không được duyệt!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseNormal updateOrder(OrdersDTO ordersDTO) {
        if(ordersDTO.getStatus() == 0 ){
            List<Orders> ordersList = orderRepository.findOrdersByStatusAndIdUser(0, ordersDTO.getIdUser());
            if(ordersList.size() == 0 ){
                Orders orders = OrdersMapper.toOrders(ordersDTO);
                User user = userRepository.findById(ordersDTO.getIdUser()).get();
                orders.setUserByIdUser(user);
                orderRepository.save(orders);
                return new ResponseNormal("Cập nhật thành công!", HttpStatus.OK);
            } else {
                return new ResponseNormal("Người dùng " + ordersDTO.getNameUser() + " đang đặt hàng, không thể cập nhật",
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            Orders orders = OrdersMapper.toOrders(ordersDTO);
            User user = userRepository.findById(ordersDTO.getIdUser()).get();
            orders.setUserByIdUser(user);
            orderRepository.save(orders);
            return new ResponseNormal("Cập nhật thành công!", HttpStatus.OK);
        }
    }

    @Override
    public ResponseNormal deleteOrder(Integer id) {
        List<OrderProduct> orderProducts = orderProductRepository.findByIdOrder(id);
        if(orderProducts.size() > 0){
            for(OrderProduct o : orderProducts){
                orderProductRepository.delete(o);
            }
        }
        orderRepository.deleteById(id);
        return new ResponseNormal("Xóa thành công!", HttpStatus.OK);
    }

    @Override
    public List<Revenue> getRevenue(String year) {
        String sub = year.substring(2, 4);
        List<Object[]> objects = orderRepository.getRevenue(sub);
        List<Revenue> revenues = new ArrayList<>();
        for(Object[] o: objects){
            Revenue revenue = new Revenue();
            revenue.setName("Tháng "+ DataUtils.safeToString(o[0]));
            revenue.setValue(DataUtils.safeToDouble(o[1]));
            revenues.add(revenue);
        }
        return revenues;
    }

    @Override
    public List<OrderProductDTO> getOrderProductByIdOrder(Integer idOrder) {
        return orderProductRepository.findByIdOrder(idOrder)
                .stream()
                .map(OrderProductMapper::toOrderProductDTO)
                .collect(Collectors.toList());
    }

}
