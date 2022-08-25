package com.migi.migi_project.service.user;

import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.model.response.ResponseNormal;

import java.util.List;

public interface BookingService {
    ResponseNormal bookService(BookingDTO booking);
    ResponseNormal updateStatus(BookingDTO booking);

    List<BookingDTO> getAllByCurrentUser(Integer userId);
    List<BookingDTO> getAll();
}
