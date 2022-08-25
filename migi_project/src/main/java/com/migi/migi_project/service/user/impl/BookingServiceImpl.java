package com.migi.migi_project.service.user.impl;

import com.migi.migi_project.entity.Booking;
import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.repository.user.BookingRepository;
import com.migi.migi_project.service.user.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseNormal bookService(BookingDTO bookingDTO) {
        try {
            Booking booking = mapper.map(bookingDTO, Booking.class);
            booking.setCreateTime(Instant.now());

            bookingRepository.save(booking);
            return new ResponseNormal("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseNormal("", HttpStatus.OK);
        }
    }
}
