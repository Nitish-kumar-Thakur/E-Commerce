package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCart {
    private Long cart_id;
    private Long user_id;
    private String user_name;
    private List<ResponseProduct> products;
}
