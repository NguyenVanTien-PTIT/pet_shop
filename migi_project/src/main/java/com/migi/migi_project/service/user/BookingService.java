package com.migi.migi_project.service.user;

import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.model.response.ResponseNormal;

public interface BookingService {
    ResponseNormal bookService(BookingDTO booking);
}
