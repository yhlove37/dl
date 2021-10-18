package ssm.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.cache.RedisCache;
import ssm.entity.Category;
import ssm.service.ProductService;

import java.util.List;

@Controller
public class CategoryListController {
    
    @Autowired
    ProductService service;
    @Autowired
    private RedisCache cache;
    
    //把数据缓存到内存当中
    @ResponseBody  //返回Json
    @RequestMapping(value = "/categoryList",
            produces = {"text/html;charset=UTF-8"})
    public String getCategoryList(){

            List<Category> categoryList = service.findAllCategory();
            //转化为gson格式
            Gson gson = new Gson();
            String categoryListJson = gson.toJson(categoryList);

        return categoryListJson;
    }
}
