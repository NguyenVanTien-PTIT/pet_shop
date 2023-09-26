package com.migi.migi_project.model.response;

import com.migi.migi_project.model.dto.ProductDTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int orderProductId;
    private int productId;
    private String shortName;
    private String mobileName;
    private String image;
    private int quantity;
    private Double subtotal;
    private ProductDTO product;
}
