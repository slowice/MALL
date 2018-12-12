package xb.mall.service.impl;

import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xb.mall.bean.ProductDetail;
import xb.mall.bean.ProductInfo;
import xb.mall.bean.requestObj.ProductInfoRequest;
import xb.mall.bean.responseObj.ProductInfoReponse;
import xb.mall.common.CommonUtils;
import xb.mall.common.Constants;
import xb.mall.common.Enums;
import xb.mall.dao.ProductDetailMapper;
import xb.mall.dao.ProductInfoMapper;
import xb.mall.dao.ProductMapper;
import xb.mall.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public List<ProductInfoReponse> getProductListByCategoryName(String categoryName) {
        return productMapper.getProductInfoListByCategoryName(categoryName);
    }

    @Override
    public List<ProductInfoReponse> getAll() {
        return productMapper.getAll();
    }

    @Override
    public List<ProductInfo> getProductListByCollectionId(String collectionId) {
        return productMapper.getProductListByCollectionId(collectionId);
    }

    @Override
    public Page<ProductInfo> getProductInfoPaged() {
        return null;
    }

    @Override
    public ProductInfoReponse getProductInfoReponseById(String productId) {
        //todo 暂时防止开发库详情表数据不全
        if(null == productDetailMapper.selectByPrimaryKey(productId))
            productDetailMapper.insert(new ProductDetail(productId));
        return productMapper.getProductInfoById(productId);
    }

    @Override
    public void saveProduct(ProductInfoRequest request){
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(request,productInfo);

        switch (request.getCategoryName()){
            case Constants.AUTOPARTS:
                productInfo.setCategoryId(Enums.AUTOPARTS.getCode());
                break;
            case Constants.BLUESPEAKER:
                productInfo.setCategoryId(Enums.BLUESPEAKERS.getCode());
                break;
            case Constants.TOY:
                productInfo.setCategoryId(Enums.TOY.getCode());
                break;
        }

        if(StringUtils.isEmpty(request.getId())){
            productInfo.setId(CommonUtils.getUUID32());
            productInfoMapper.insert(productInfo);
            productMapper.insertProductDetail(request);
        } else {
            productInfoMapper.updateByPrimaryKey(productInfo);
            productMapper.updateProductDetailById(request);
        }
    }

    @Override
    public void deleteProduct(String productId) {
        productInfoMapper.deleteByPrimaryKey(productId);
    }

}
