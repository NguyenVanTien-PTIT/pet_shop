package com.migi.migi_project.model.mapper;

import com.migi.migi_project.entity.Orders;
import com.migi.migi_project.model.dto.OrdersDTO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersMapper {
    public static OrdersDTO toOrdersDTO (Orders orders){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setId(orders.getId());
        ordersDTO.setTotalprice(orders.getTotalprice());
        ordersDTO.setAddress(orders.getAddress());
        ordersDTO.setPhoneNumber(orders.getPhoneNumber());
        ordersDTO.setNameUser(orders.getNameUser());
        //Convert Date -> String
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String result = simpleDateFormat.format(orders.getOrderDate());
        ordersDTO.setOrderDate(result);
        ordersDTO.setStatus(orders.getStatus());
        ordersDTO.setIdUser(orders.getUserByIdUser().getId());

        ordersDTO.setOrderIdMobile("MHD00" + orders.getId());
        if (orders.getStatus() == 0) {
            ordersDTO.setStatusDisplay("Đang đặt hàng");
        } else if (orders.getStatus() == 1) {
            ordersDTO.setStatusDisplay("Đang đợi xác nhận");
        } else if (orders.getStatus() == 2) {
            ordersDTO.setStatusDisplay("Đang giao hàng");
        } else {
            ordersDTO.setStatusDisplay("Đã giao hàng");
        }
        ordersDTO.setOrderDateMobile(formatter.format(orders.getOrderDate()));
        return ordersDTO;
    }

    public static Orders toOrders (OrdersDTO ordersDTO){
        Orders orders = new Orders();
        orders.setId(ordersDTO.getId());
        orders.setTotalprice(ordersDTO.getTotalprice());
        orders.setAddress(ordersDTO.getAddress());
        orders.setPhoneNumber(ordersDTO.getPhoneNumber());
        orders.setNameUser(ordersDTO.getNameUser());
        //Convert String -> Timestamp
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(ordersDTO.getOrderDate());
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            orders.setOrderDate(timestamp);
        } catch(Exception e) {
            e.printStackTrace();
        }
        orders.setStatus(ordersDTO.getStatus());
        return orders;
    }
}
