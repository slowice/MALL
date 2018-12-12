package xb.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xb.mall.bean.ProductDetail;

@Mapper
@Repository
public interface ProductDetailMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ProductDetail record);

    ProductDetail selectByPrimaryKey(String productId);

    List<ProductDetail> selectAll();

    int updateByPrimaryKey(ProductDetail record);
}