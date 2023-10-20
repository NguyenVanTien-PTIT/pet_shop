package com.migi.migi_project.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUploadFile<T> {
    private String msg;
    private HttpStatus httpStatus;
    private T data;
}
