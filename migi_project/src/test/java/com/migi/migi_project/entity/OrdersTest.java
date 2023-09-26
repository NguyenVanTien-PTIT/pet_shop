package com.migi.migi_project.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class OrdersTest {
    @Test
    void testConstructor() {
        Orders actualOrders = new Orders();
        actualOrders.setAddress("42 Main St");
        actualOrders.setId(1);
        actualOrders.setNameUser("Name User");
        actualOrders.setOrderDate(mock(Timestamp.class));
        actualOrders.setOrderProductsById(null);
        actualOrders.setPhoneNumber("4105551212");
        actualOrders.setStatus(1);
        actualOrders.setTotalprice(10.0);
        User user = new User();
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setUserRolesById(null);
        user.setId(1);
        user.setCreateDate(mock(Timestamp.class));
        user.setOrdersById(null);
        user.setPhoneNumber("4105551212");
        user.setAddress("42 Main St");
        user.setFullname("Dr Jane Doe");
        actualOrders.setUserByIdUser(user);
        assertEquals("42 Main St", actualOrders.getAddress());
        assertEquals(1, actualOrders.getId());
        assertEquals("Name User", actualOrders.getNameUser());
        assertNull(actualOrders.getOrderProductsById());
        assertEquals("4105551212", actualOrders.getPhoneNumber());
        assertEquals(1, actualOrders.getStatus());
        assertEquals(10.0, actualOrders.getTotalprice().doubleValue());
        assertSame(user, actualOrders.getUserByIdUser());
    }
}

