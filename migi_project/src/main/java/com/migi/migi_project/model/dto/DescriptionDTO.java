package com.migi.migi_project.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DescriptionDTO {
    private String name;
    private String value;
}
