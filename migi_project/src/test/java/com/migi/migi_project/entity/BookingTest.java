package com.migi.migi_project.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BookingTest {
    @Test
    void testConstructor() {
        Booking actualBooking = new Booking();
        actualBooking.setAppointmentDate(null);
        actualBooking.setClientId(123);
        actualBooking.setCreateTime(null);
        actualBooking.setId(1);
        actualBooking.setPetName("Bella");
        actualBooking.setPetType("Dog");
        actualBooking.setPhone("4105551212");
        actualBooking.setServiceId(123);
        actualBooking.setServiceWorkerId(123);
        actualBooking.setStatus(1);
        assertNull(actualBooking.getAppointmentDate());
        assertEquals(123, actualBooking.getClientId().intValue());
        assertNull(actualBooking.getCreateTime());
        assertEquals(1, actualBooking.getId().intValue());
        assertEquals("Bella", actualBooking.getPetName());
        assertEquals("Dog", actualBooking.getPetType());
        assertEquals("4105551212", actualBooking.getPhone());
        assertEquals(123, actualBooking.getServiceId().intValue());
        assertEquals(123, actualBooking.getServiceWorkerId().intValue());
        assertEquals(1, actualBooking.getStatus().intValue());
    }
}

