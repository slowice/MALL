package xb.mall.service;

import org.springframework.web.multipart.MultipartFile;
import xb.mall.bean.IndexImage;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    /**
     * 根据图片类别，查询该类下所有图片
     * @return
     * @throws Exception
     */
    List<IndexImage> getIndexImageByImageCategroy(String imgCategory) throws Exception;

    Integer storeProductDetailImg(MultipartFile file,String productId) throws IOException;
}
