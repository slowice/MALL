package xb.mall.service;

import xb.mall.bean.ProductInfo;

import java.util.List;

public interface ProductCollectionService {
    void addRelationBatch(List<String> productId, String collectionId);
    void deleteRelation(String productId,String collectionId);
}
