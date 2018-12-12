package xb.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfo {
    private String id;

    private String name;

    private Integer stock;

    private String imgPath;

    private BigDecimal price;

    private String description;

    private String categoryId;

    private Integer sold;
}