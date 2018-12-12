package xb.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xb.mall.bean.ProductInfo;

@Mapper
@Repository
public interface ProductInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(String id);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);
}