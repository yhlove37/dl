package ssm.controller.admin;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ssm.entity.Product;
import ssm.service.AdminProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class AdminUpdateProductController {

    @Autowired
    AdminProductService service;

    @RequestMapping(value = "/adminUpdateProduct", method = RequestMethod.POST)
    public String UpdateProduct(HttpServletRequest request,@ModelAttribute Product product,@RequestParam(value = "upload",required = false) CommonsMultipartFile upload) throws IOException {
        if (!upload.isEmpty()){
            String path = request.getServletContext().getRealPath("/products/1");
            System.out.println("查看地址"+path);
            File realPath = new File(path);
            if (!realPath.exists()){
                realPath.mkdir();
            }
            //上传文件地址
            System.out.println("上传文件保存地址："+realPath);
            upload.transferTo(new File(realPath +"/"+ upload.getOriginalFilename()));

//        //本地地址
            String path2="C:\\Users\\yh139\\Desktop\\p3=\\SSM-Maven\\SSM-Maven\\src\\main\\webapp\\products\\1";
            File realPath2 = new File(path2);
            if (!realPath2.exists()){
                realPath2.mkdir();
            }
            System.out.println("上传文件保存地址："+realPath2);
            upload.transferTo(new File(realPath2 +"/"+ upload.getOriginalFilename()));
            String pimage ="products/1/"+upload.getOriginalFilename();
            product.setPimage(pimage);
        }else {
            product.setPimage(null);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String pDate = format.format(new Date());
        product.setPdate(pDate);
        System.out.println(product);
        service.updateProduct(product);

        return "redirect:/adminProductList";
    }
}
