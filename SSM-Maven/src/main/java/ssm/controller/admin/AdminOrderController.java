package ssm.controller.admin;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.entity.Order;
import ssm.entity.OrderItem;
import ssm.service.AdminProductService;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminOrderController {
    @Autowired
    AdminProductService service;

    /**
     * @Author YH
     * @Description //查询全部订单，根据订单的uid 查询用户信息
     * @Date 6:00 PM 10/25/2021
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/findAllOrderss")
    public String findAllOrders(HttpServletRequest request){
        List<Order> orderList = service.findAllOrders();

        System.out.println(orderList);
        request.setAttribute("orderList", orderList);
        //可知uid对应用户
        return "admin/order/list";
//       return  "admin2/page/page1";
    }
    
    //根据订单id查询订单项和商品信息
    @ResponseBody
    @RequestMapping(value = "/findOrderInfoByOid",
        produces = {"text/html;charset=UTF-8"})
    public String findOrderInfoByOid(HttpServletRequest request){
        try {
            //2秒后执行
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String oid = request.getParameter("oid");
        List<OrderItem> orderItemList = service.finOrderInfoByOid(oid);
        Gson gson = new Gson();
        System.out.println("gogogogogoogo"+gson.toJson(orderItemList));
        return gson.toJson(orderItemList);
    }
}
