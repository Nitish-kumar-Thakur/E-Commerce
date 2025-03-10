package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertProduct {
    private String name;
    private String imageUrl;
    private Double price;
    private String description;
    private Long user;
    private List<Long> categories;
}
