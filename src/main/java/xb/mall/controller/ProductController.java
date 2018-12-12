package xb.mall.controller;

import com.github.pagehelper.Page;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xb.mall.bean.ProductInfo;
import xb.mall.bean.responseObj.ProductInfoReponse;
import xb.mall.common.Enums;
import xb.mall.common.ImageUtil;
import xb.mall.common.vo.ResultVO;
import xb.mall.common.vo.ResultVOUtil;
import xb.mall.service.ProductService;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping("/getProductInfoPaged")
    public Page<ProductInfo> getProductInfoPaged(@RequestParam String category){
        Page<ProductInfo> page = productService.getProductInfoPaged();
        return page;
    }

    /**
     * 根据产品类型查询所有产品
     * @param categoryName
     * @return
     */
    @RequestMapping("/getProductInfoListByCategoryName")
    public List<ProductInfoReponse> getProductInfoList(@RequestParam String categoryName){
        List<ProductInfoReponse> list = productService.getProductListByCategoryName(categoryName);
        return list;
    }

    /**
     * 查询特色产品列表
     * @return
     */
    @GetMapping("/features/getAll")
    public ResultVO getAllFeatures(){
        List<ProductInfo> obj = productService.getProductListByCollectionId(Enums.FEATUREPRODUCT.getCode());
        return ResultVOUtil.success(obj);
    }

    /**
     * 返回NODE类型的产品信息供运营端使用
     * @return
     */
    @RequestMapping("/getAll")
    public List<Node> getProductInfoList(){
        Map<String,List<ProductInfoReponse>> map = new HashMap<>();
        List<ProductInfoReponse> list = productService.getAll();
        //构造ztree所需要的数据结构
        map = list.stream().collect(Collectors.groupingBy(ProductInfoReponse :: getCategoryName, TreeMap::new,Collectors.toList()));
        List<Node> nodes = new ArrayList<>();
        Set<Map.Entry<String,List<ProductInfoReponse>> > set = map.entrySet();
        for(Map.Entry<String,List<ProductInfoReponse>> entry:set){
            Node node = new Node();
            node.setName(entry.getKey());
            List<Node> children = new ArrayList<>();
            for(ProductInfoReponse reponse:entry.getValue()){
                Node childNode = new Node();
                childNode.setName(reponse.getName());
                childNode.setId(reponse.getId());
                children.add(childNode);
            }
            node.setChildren(children);
            nodes.add(node);
        }
        return nodes;
    }


    @Data
    public static class Node{
        private String id;
        private String name;
        private List<Node> children;
    }
}
