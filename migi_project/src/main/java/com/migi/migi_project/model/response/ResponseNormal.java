package com.migi.migi_project.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseNormal {
    private String msg;
    private HttpStatus httpStatus;
}
