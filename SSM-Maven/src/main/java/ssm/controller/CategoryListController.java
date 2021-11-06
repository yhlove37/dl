package ssm.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.cache.RedisCache;
import ssm.entity.Category;
import ssm.service.AdminProductService;
import ssm.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryListController {
    
    @Autowired
    ProductService service;
    @Autowired
    private RedisCache cache;
    @Autowired
    AdminProductService adminProductService;

    @ResponseBody
    @RequestMapping(value = "/categoryList",
            produces = {"text/html;charset=UTF-8"})
    public String getCategoryList(){

            List<Category> categoryList = service.findAllCategory();

            //转化为gson格式
            Gson gson = new Gson();
            String categoryListJson = gson.toJson(categoryList);

        return categoryListJson;
    }


    @RequestMapping(value = "/adminCatagory",
            produces = {"text/html;charset=UTF-8"})
    public String adminCatagory(HttpServletRequest request){
        List<Category> categoryList = service.findAllCategory();
        request.setAttribute("categoryList",categoryList);

        return "admin/catagory/index";
    }


    /**
     * @Author YH
     * @Description //分类列表的删除数据
     * @Date 3:19 PM 11/5/2021
     * @Param [request, cid]
     * @return java.lang.String
     **/
    @ResponseBody
    @RequestMapping(value = "/deleterow",produces = {"text/html;charset=UTF-8"})
    public String deleterow(HttpServletRequest request,String cid){
        boolean flag = adminProductService.deleteRow(cid);
        String msg="";
        if (flag){
            msg="更新成功";
        }else {
            msg="更新失败";
        }
        Gson gson = new Gson();
        msg = gson.toJson(msg);

        return msg;
    }

    /**
     * @Author YH
     * @Description //分类列表的更新数据
     * @Date 3:18 PM 11/5/2021
     * @Param [request]
     * @return void
     **/
    @ResponseBody
    @RequestMapping(value = "/updaterow",produces = {"text/html;charset=UTF-8"})
    public void updaterow(HttpServletRequest request){
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        String cinfo= request.getParameter("cinfo");
        Category category = new Category();
        category.setCinfo(cinfo);
        category.setCname(cname);
        category.setCstate(1);
        if (cid != null && cid.length() != 0){
            category.setCid(cid);
            adminProductService.updateRow(category);
        }else{
            int i = adminProductService.selectRowCount();
            category.setCid(String.valueOf(i+1));
            adminProductService.insertRow(category);
        }
    }
}
