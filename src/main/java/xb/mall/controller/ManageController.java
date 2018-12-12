package xb.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xb.mall.bean.requestObj.ProductInfoRequest;
import xb.mall.bean.responseObj.ProductInfoReponse;
import xb.mall.common.ImageUtil;
import xb.mall.service.MenuService;
import xb.mall.service.ProductService;

import java.io.*;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private ProductService productService;

    @Value("${web.upload-path}")
    private String imgStorePath;

    @RequestMapping("/")
    public ModelAndView manage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manage_product_list");
        modelAndView.addObject("menuPid","product");
        modelAndView.addObject("menuId","autoParts");
        return modelAndView;
    }

    /**
     * 根据产品分类名称，进入产品列表页
     * @param productCategory
     * @return
     */
    @RequestMapping("/product/{productCategory}")
    public ModelAndView getProductList(@PathVariable String productCategory){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manage_product_list");
        modelAndView.addObject("menuPid","product");
        modelAndView.addObject("menuId",productCategory);
        return modelAndView;
    }

    /**
     * 根据产品ID进入详情页
     * @param productId
     * @return
     */
    @RequestMapping("/productdetail")
    public ModelAndView getProductDetail(@RequestParam(required = false) String productId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manage_product_detail");
        if (StringUtils.isEmpty(productId)){
            modelAndView.addObject("productInfo",new ProductInfoReponse());
        }else {
            ProductInfoReponse productInfo = productService.getProductInfoReponseById(productId);
            modelAndView.addObject("productInfo",productInfo);
        }

        return modelAndView;
    }

    /**
     * 保存产品
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/product/save")
    public ModelAndView saveProduct(ProductInfoRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        MultipartFile image = request.getImage();
        if(image != null){
            String filename = request.getId()+".jpg";
            ImageUtil.storeImg(image,imgStorePath,filename,230,187);
        }
        productService.saveProduct(request);
        modelAndView.setViewName("manage_product_list");
        modelAndView.addObject("menuPid","product");
        modelAndView.addObject("menuId",request.getCategoryName());
        return modelAndView;
    }

    @RequestMapping("/product/delete")
    public ModelAndView deleteProduct(@RequestParam String productId){
        ModelAndView modelAndView = new ModelAndView();
        productService.deleteProduct(productId);
        modelAndView.setViewName("manage_product_list");
        modelAndView.addObject("menuPid","product");
        modelAndView.addObject("menuId","autoParts");
        return modelAndView;
    }


    @RequestMapping("/order")
    public ModelAndView getOrderList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manage_order_list");
        modelAndView.addObject("menu","order");

        return modelAndView;
    }
}
