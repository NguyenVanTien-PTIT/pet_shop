package com.migi.migi_project.controller.user;

import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.service.user.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "booking")
    public ResponseEntity<?> bookService(@RequestBody BookingDTO booking){
        return ResponseEntity.ok(bookingService.bookService(booking));
    }
}
