package xb.mall.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.mall.MallApplicationTests;
import xb.mall.bean.requestObj.ProductInfoRequest;
import xb.mall.bean.responseObj.ProductInfoReponse;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest extends MallApplicationTests {
    @Autowired
    private ProductService productService;

    @Test
    public void getProductListAll() {
    }

    @Test
    public void getProductListByCategoryName() {
        String categoryName = "autoParts";
        List<ProductInfoReponse> reponses = productService.getProductListByCategoryName(categoryName);
        Assert.assertNotNull(reponses);
        System.out.println(reponses.size());
    }

    @Test
    public void getProductInfoPaged() {
    }

    @Test
    public void getProductInfoById() {
        String productId = "6703553ae89c11e8847c83fe7038c841";
        ProductInfoReponse reponse = productService.getProductInfoReponseById(productId);
        Assert.assertNotNull(reponse);
        System.out.println(reponse.getCategoryName());
    }

    @Test
    public void saveProduct() {

    }
}