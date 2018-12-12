package xb.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xb.mall.bean.IndexImage;
import xb.mall.common.Enums;
import xb.mall.common.FileUtil;
import xb.mall.common.ImageUtil;
import xb.mall.common.vo.ResultVO;
import xb.mall.common.vo.ResultVOUtil;
import xb.mall.service.ImageService;
import xb.mall.service.ProductCollectionService;
import xb.mall.service.ProductService;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
    private static final String URL_PRE = "manage_homepage_";

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCollectionService productCollectionService;

    @Autowired
    private ImageService imageService;

    @Value("${web.upload-path}")
    private String imgStorePath;

    /**
     * Homepage菜单路由
     * @param part
     * @return
     */
    @GetMapping("/{part}")
    public ModelAndView routes(@PathVariable String part){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(URL_PRE+part);
        modelAndView.addObject("menuPid","homepage");
        modelAndView.addObject("menuId",part);
        return modelAndView;
    }

    /**
     * 添加特色产品
     * @return
     */
    @PostMapping("/features/add")
    @ResponseBody
    public ResultVO addFeature(@RequestParam String[] productIdArr){
        if(null!= productIdArr){
            List<String> productId = Arrays.asList(productIdArr);
            productCollectionService.addRelationBatch(productId, Enums.FEATUREPRODUCT.getCode());
        }
        return ResultVOUtil.success(null);
    }

    /**
     * 删除特色产品
     * @param productId
     * @return
     */
    @PostMapping("/features/delete")
    @ResponseBody
    public ResultVO deleteFeatures(@RequestParam String productId){
        productCollectionService.deleteRelation(productId,Enums.FEATUREPRODUCT.getCode());
        return ResultVOUtil.success(null);
    }
}
