package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {
    private Long id;
    private String name;
    private String imageUrl;
    private Double price;
    private String description;
    private Long vendor_id;
    private String vendor_name;
    private List<ResponseCategory> categories;
}
