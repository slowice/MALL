package xb.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.mall.dao.ProductMapper;
import xb.mall.service.ProductCollectionService;

import java.util.List;

@Service
public class ProductCollectionSerivceImpl implements ProductCollectionService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public void addRelationBatch(List<String> productId, String collectionId) {
        productMapper.addProductCollectionRelationBatch(productId,collectionId);
    }

    @Override
    public void deleteRelation(String productId, String collectionId) {
        productMapper.deleteProductCollectionRelation(productId,collectionId);
    }
}
