package com.migi.migi_project.repository.user;

import com.migi.migi_project.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
