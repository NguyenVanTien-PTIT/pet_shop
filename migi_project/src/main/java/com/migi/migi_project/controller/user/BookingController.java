package com.migi.migi_project.controller.user;

import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.service.user.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "booking")
    public ResponseEntity<?> bookService(@RequestBody BookingDTO booking){
        return ResponseEntity.ok(bookingService.bookService(booking));
    }

    @PutMapping(value = "booking/status")
    public ResponseEntity<?> updateStatus(@RequestBody BookingDTO booking){
        return ResponseEntity.ok(bookingService.updateStatus(booking));
    }

    @GetMapping(value = "booking/user/{user-id}")
    public ResponseEntity<?> getAllByCurrentUser(@PathVariable(value = "user-id") Integer userId){
        return ResponseEntity.ok(bookingService.getAllByCurrentUser(userId));
    }

    @GetMapping(value = "booking")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(bookingService.getAll());
    }
}
