package xb.mall.bean.responseObj;

import lombok.Data;
import xb.mall.bean.ProductDetail;

import java.math.BigDecimal;

@Data
public class ProductInfoReponse {
    private String id;
    private String name;
    private Integer stock;
    private Integer sold;
    private Integer stars;
    private String imgPath;
    private BigDecimal price;
    private String description;
    private String categoryName;
    private String categoryDesc;
    private String categoryId;
    private String detailDesc;
    private String specificationTable;
}
