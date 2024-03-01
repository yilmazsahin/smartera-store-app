package com.smartera.orderservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yilmazsahin
 * @since 3/2/2024
 */

@Getter
@Setter
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private double price;
    private long size;
}
