package xb.mall.bean.requestObj;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductInfoRequest {
    private String id;
    private String name;
    private Integer stock;
    private Integer sold;
    private String imgPath;
    private BigDecimal price;
    private String description;
    private String categoryName;
    private MultipartFile image;
    private String detailDesc;
    private String specificationTable;
}
