package ssm.controller.admin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ssm.entity.Product;
import ssm.entity.User;
import ssm.service.AdminProductService;
import ssm.utils.CommonsUtils;
import ssm.vo.ProductVo;
import sun.reflect.FieldInfo;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminAddProductController {
    @Autowired
    AdminProductService service;

    //添加商品
    @RequestMapping(value = "/adminAddProduct", method = RequestMethod.POST)
    public String addProduct(HttpServletRequest request,@ModelAttribute Product product,@RequestParam(value = "upload",required = false) CommonsMultipartFile upload) throws IOException {
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
        service.addProduct(product);

        return "redirect:/adminProductList";

    }
    
}
