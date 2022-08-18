package com.migi.migi_project.model.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceWorkerDTO {
    private Integer id;

    private String fullname;

    private String phoneNumber;

    private String avatar;

    private String address;

    private String description;

    private Instant createTime;
}
