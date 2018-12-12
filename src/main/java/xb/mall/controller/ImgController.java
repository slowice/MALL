package xb.mall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xb.mall.bean.IndexImage;
import xb.mall.common.FileUtil;
import xb.mall.common.ImageUtil;
import xb.mall.common.vo.ResultVO;
import xb.mall.common.vo.ResultVOUtil;
import xb.mall.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class ImgController {
    @Value("${web.upload-path}")
    private String imgStorePath;

    @Autowired
    ImageService imageService;

    /**
     * 商品详情图片上传（多张）
     * @return
     */
    @RequestMapping("/product/img/upload")
    @ResponseBody
    public String middleImageUpload(@RequestParam("img") MultipartFile file,@RequestParam(value="productId",required = false) String productId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map result = new HashMap();

        Integer index = imageService.storeProductDetailImg(file,productId);

        List<String> imgUrls = new LinkedList<>();
        String imgUrl = "/mall/productdetail/preview?productId="+productId+"&index="+index;
        imgUrls.add(imgUrl);
        result.put("data",imgUrls);
        result.put("errno",0);
        return mapper.writeValueAsString(result);
    }

    /**
     * 商品主图片预览
     * @param productId
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping(value = "/product/preview",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity preview(@RequestParam String productId) throws FileNotFoundException {
        String filePath = imgStorePath;
        String fileName = productId+".jpg";
        return ImageUtil.preview(filePath,fileName);
    }

    /**
     * 商品详情图片预览
     * @param productId
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping(value = "/productdetail/preview",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity detailPreview(@RequestParam String productId,@RequestParam Integer index) throws FileNotFoundException {
        String filePath = imgStorePath+productId+"/";
        String fileName = index+".jpg";
        return ImageUtil.preview(filePath,fileName);
    }

    /**
     * 上传banner图片
     * @param param
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/homepage/bannerUpload")
    @ResponseBody
    public ResultVO bannerUpload(@RequestParam Map<String, String> param,@RequestParam MultipartFile file) throws IOException {
        String imgCategory = param.get("uid");
        String imgIndex = param.get("id").substring(param.get("id").length()-1);
        String fileName = imgCategory + imgIndex + ".jpg";
        String filePath = imgStorePath + "banner/";
        ImageUtil.storeImg(file,filePath,fileName,1660,480);
        return ResultVOUtil.success(null);
    }

    /**
     * 根据菜单名称作为图片类别，上传多张图片
     */
    @PostMapping("/{imgCategoryId}/img/upload")
    @ResponseBody
    public ResultVO imgUpload(@PathVariable String imgCategoryId,@RequestParam Map<String, String> param,@RequestParam MultipartFile file) throws IOException {
        String imgCategory = param.get("uid");
        String imgIndex = param.get("id").substring(param.get("id").length()-1);
        String fileName = imgCategory + imgIndex + ".jpg";
        String filePath = imgStorePath + imgCategoryId + "/";
        ImageUtil.storeImg(file,filePath,fileName,1660,480);
        return ResultVOUtil.success(null);
    }

    @PostMapping("/homepage/{menuId}/img/clean")
    @ResponseBody
    public ResultVO imgClean(@PathVariable String menuId) throws IOException {
        String filePath = imgStorePath + menuId + "/";
        FileUtil.cleanDir(filePath);
        return ResultVOUtil.success(null);
    }

    /**
     * 把菜单名称当图片类别，查询该类下所有图片
     * @param menuId
     * @return
     * @throws Exception
     */
    @GetMapping("/homepage/{menuId}/img/preview")
    @ResponseBody
    public ResultVO imgPreview(@PathVariable String menuId) throws Exception {
        List<IndexImage> indexImageList = imageService.getIndexImageByImageCategroy(menuId);
        return ResultVOUtil.success(indexImageList);
    }

    @PostMapping("/ckupload")
    @ResponseBody
    public void ckeditorImageHandler(@RequestParam("upload") MultipartFile file, @RequestParam String productId, @RequestParam String CKEditorFuncNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Integer index = imageService.storeProductDetailImg(file,productId);
        //todo 存储图片
        Integer index = imageService.storeProductDetailImg(file,productId);
        String imgUrl = "/mall/productdetail/preview?productId="+productId+"&index="+index;
        String callback = request.getParameter("CKEditorFuncNum");
        String result = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + imgUrl + "');</script>";
        response.getWriter().write(result);
    }
}
