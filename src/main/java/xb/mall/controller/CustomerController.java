package xb.mall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.nio.cs.ext.HKSCS;
import xb.mall.bean.IndexImage;
import xb.mall.bean.ProductInfo;
import xb.mall.bean.responseObj.ProductInfoReponse;
import xb.mall.common.Enums;
import xb.mall.common.vo.ResultVO;
import xb.mall.common.vo.ResultVOUtil;
import xb.mall.service.ImageService;
import xb.mall.service.ProductService;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
@Slf4j
@PropertySource(value="classpath:application.properties",encoding = "UTF-8")
    public class CustomerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Value("${myaddress}")
    private String myaddress;
    @Value("${mobile}")
    private String mobile;
    @Value("${email}")
    private String email;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer_home");
        Map<String,Object> data = new HashMap<>();
        List<ProductInfo> featuresList = productService.getProductListByCollectionId(Enums.FEATUREPRODUCT.getCode());

        List<IndexImage> middleAdsList = new LinkedList<>();
        //特色产品
        if(featuresList.size()>4){
            data.put("features1",featuresList.subList(0,4));
            data.put("features2",featuresList.subList(4,featuresList.size()));
        }else {
            data.put("features1",featuresList.subList(0,featuresList.size()));
        }
        //中栏广告
        try {
            middleAdsList = imageService.getIndexImageByImageCategroy(Enums.MIDDLEADS.getValue());
            data.put("middleAdsList",middleAdsList);
        } catch (Exception e){
            log.error("广告图片查询错误");
        }
        //分类产品
        List<ProductInfoReponse> productInfoReponseList = productService.getAll();
        Map<String,List<ProductInfoReponse>> map = new HashMap<>();
        map = productInfoReponseList.stream().collect(Collectors.groupingBy(ProductInfoReponse :: getCategoryName, TreeMap::new,Collectors.toList()));
        for(Map.Entry<String,List<ProductInfoReponse>> entry:map.entrySet()){
            List<ProductInfoReponse> list = entry.getValue();
            if(list.size()>3){
                list = list.subList(0,3);
            }
            data.put(entry.getKey(),list);
        }
        //联系我们
        data.put("email",email);
        data.put("mobile",mobile);
        data.put("address", myaddress);
        modelAndView.addAllObjects(data);
        return modelAndView;
    }

    @GetMapping("/product/detail/{id}")
    public ModelAndView productDetail(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer_product_detail");
        ProductInfoReponse productInfo = productService.getProductInfoReponseById(id);

        modelAndView.addObject("productInfo",productInfo);
        return modelAndView;
    }

    @RequestMapping("/commonInfo")
    @ResponseBody
    public String getCommonInfo() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> commonInfo = new HashMap<>();
        commonInfo.put("email",email);
        commonInfo.put("mobile",mobile);
        commonInfo.put("address", myaddress);

        return mapper.writeValueAsString(commonInfo);
    }
}
