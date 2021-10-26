package ssm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.entity.*;
import ssm.service.ProductService;
import ssm.service.UserService;
import ssm.utils.CommonsUtils;
import ssm.vo.Cart;
import ssm.vo.CartItem;
import ssm.vo.PageBean;
import ssm.vo.ProductVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class ProductController {
    
    @Autowired
    ProductService service;

    @Autowired
    UserService userService;
    
    // 实现分页获取商品列表
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String getProductList(HttpServletRequest request){

        //获得当前页  从request域中取出的数据都是string
        String currentPageStr = request.getParameter("currentPage");
        String cid = request.getParameter("cid");


        // 如果为首次访问，则默认显示第一页
        if(currentPageStr==null) currentPageStr="1";
        int currentPage = Integer.parseInt(currentPageStr);
//        int currentPage = 1;
        // 认为每页显示12条
        int currentCount = 12;
        PageBean pageBean = service.findPageBean(currentPage, currentCount);
        request.setAttribute("pageBean", pageBean);
        return "/product_list";
    }
    
    // 加载有热门商品和最新商品的页面
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest request) throws UnknownHostException, UnsupportedEncodingException {
        // 准备热门商品
        List<ProductVo> hotProductList = service.findHotProductList();
        //准备最新商品
        List<ProductVo> newProductList = service.findNewProductList();
        request.setAttribute("hotProductList", hotProductList);
        request.setAttribute("newProductList", newProductList);
        return "index";
    }
    
    //获取分类后的分页数据
    @RequestMapping(value = "/productListByCid")
    public String getProductListByCid(HttpServletRequest request){
        //获取Cid
        String cid = request.getParameter("cid");
        //当前页
        String currentPageStr = request.getParameter("currentPage");

        String sy = request.getParameter("sy");
        System.out.println(sy);

            if(currentPageStr == null) currentPageStr = "1";
            int currentPage = Integer.parseInt(currentPageStr);
            //每页显示数
            int currentCount = 12;
            //cid 类别的id
            //currentPage 每页显示的数量
            //通过service得到pageBean
            PageBean pageBean = service.findPageBeanByCid(cid, currentPage, currentCount);

            request.setAttribute("pageBean", pageBean);
            request.setAttribute("cid", cid);

            //定义一个根据cookie记录历史商品记录的集合
            List<Product> historyProductList = new ArrayList<>();
            //从客户端获得名为pids的cookie
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if("pids".equals(cookie.getName())){
                        String pids = cookie.getValue(); //3-2-1
                        System.out.println("cookie存数据的方式666666666"+pids);
                        String[] split = pids.split("-");
                        for(String pid : split){
                            Product product = service.findProductByPid(pid);
                            historyProductList.add(product);
                        }
                    }
                }
            }
            //将历史记录的集合放到域中
            request.setAttribute("historyProductList", historyProductList);

            return "/product_list";



    }

    /**
     * 根据 pid 查看商品的详细信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/productInfo")
    public String getProductInfo(HttpServletRequest request, HttpServletResponse response){
        //获得商品id
        String pid = request.getParameter("pid");
        //由于要返回上一页，需要参数cid&currentPage
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String sy = request.getParameter("sy");



        //根据pid得到商品信息
        Product product = service.findProductByPid(pid);
        
        request.setAttribute("product", product);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("cid", cid);
        request.setAttribute("sy",sy);

        //接下来是将获得客户端带来的cookie,将当前的pid加入到cookie中，
        //产生新的cookies,再发送到客户端。
        //1、pids为cookie
        String pids= pid;
        System.out.println("**********"+pids);
        //2、从request域中获得Cookies.
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                //确定得到的是pids的的这个cookie
                if("pids".equals(cookie.getName())){
                    pids = cookie.getValue();
                    //1-3-2 本次访问商品pid是8----->8-1-3-2
                    //1-3-2 本次访问商品pid是3----->3-1-2
                    //1-3-2 本次访问商品pid是2----->2-1-3
                    //将pids拆成一个数组
                    System.out.println("================================"+pids);
                    String[] split = pids.split("-"); //{3,1,2}
                    List<String> asList = Arrays.asList(split);  //[3,1,2]
                    LinkedList<String> list = new LinkedList<String>(asList); //[3,1,2]
                    //判断集合中是否存在当前pid,如果存在，则删除它，然后再首部再添加pid
                    if(list.contains(pid)){
                        list.remove(pid);
                    }
                    //否则添加到cookie
                    list.addFirst(pid);
                    //再将[3,1,2]转成3-1-2的字符串
                    StringBuffer sb = new StringBuffer();
                    for(int i=0; i<list.size() && i<7; i++){
                        sb.append(list.get(i));
                        sb.append("-");
                    }
                    //去掉3-1-2-后的 -
                    pids = sb.substring(0, sb.length() - 1);
                }
            }
        }
        //构建一个cookie对象（对应的key-值）
        Cookie cookie_pids = new Cookie("pids", pids);
        response.addCookie(cookie_pids);
        return "/product_info";

    }

    /**
     * @Author YH
     * @Description 添加商品到购物车中，购物车为商品的map集合
     * @Date 3:25 PM 10/25/2021
     * @Param [request, session]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/addProductToCart")
    public String addProductToCart(HttpServletRequest request, HttpSession session){
        //获取商品的 pid 和 需要购买的数量
        String pid = request.getParameter("pid");
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));

        // 封装CartItem
        //--根据pid查询商品
        //ProductVo 表示有类别的商品
        ProductVo productVo = service.findProductVoByPid(pid);
        //--计算小计 subTotal
        double subTotal = productVo.getShop_price() * buyNum;
        //完整的一件商品的订单，包含数量，和总价格
        CartItem item = new CartItem();
        item.setProductVo(productVo);
        item.setBuyNum(buyNum);
        item.setSubTotal(subTotal);
        
        //获取购物车，首先判断session中有没有购物车
        //购物车则是CartItem的集合 以及总价格
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }
        //将购物项放到车中---key是pid
        //先判断购物车中是否已将包含此购物项了 ----- 判断key是否已经存在

        //如果购物车中已经存在该商品----将现在买的数量与原有的数量进行相加操作
        //得到购物车中的商品项集合
        Map<String, CartItem> cartItems = cart.getCartItems();
        double newSubtotal = 0.0;
        //如果此时添加的商品项与在购物车的集合中商品项存在，更新该项的商品数据（购买数量，购买总价）
        if(cartItems.containsKey(pid)){
            //取出购买数量，再将新增的数量加到原来的购买数量中
            int oldBuyNum = cartItems.get(pid).getBuyNum();
            oldBuyNum = oldBuyNum + buyNum;
            cartItems.get(pid).setBuyNum(oldBuyNum);
            //修改小计
            double oldSubtotal = cartItems.get(pid).getSubTotal();
            newSubtotal = buyNum * productVo.getShop_price();
            cartItems.get(pid).setSubTotal(oldSubtotal + newSubtotal);
        }else{
            // cart中没有该商品 就好办了不是吗，将新添加的商品放入map集合中
            cart.getCartItems().put(productVo.getPid(), item);
            newSubtotal = buyNum * productVo.getShop_price() + newSubtotal;
        }
        //计算购物车 之前的购物车呢 所以要相加 总计
        double total = cart.getTotal() + newSubtotal;
        cart.setTotal(total);
        session.setAttribute("cart", cart);
        return "redirect:/cart.jsp";
    }
    
    //从购物车删除单个商品订单。
    @RequestMapping(value = "/delProductFromCart")
    public String delProductFromCart(HttpServletRequest request, HttpSession session){
        //获取要删除的商品的pid
        String pid = request.getParameter("pid");
        //获取session中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart!=null){
            Map<String, CartItem> cartItems = cart.getCartItems();
            //改变购物车总价
            double total = cart.getTotal() - cart.getCartItems().get(pid).getSubTotal();
            cart.setTotal(total);
            //删除cartItems中的指定商品pid的商品。
            cartItems.remove(pid);
            cart.setCartItems(cartItems);
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart.jsp";
    }
    
    //清空购物车
    @RequestMapping(value = "/clearCart")
    public String clearCart(HttpServletRequest request, HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart.jsp";
    }
    
    
    /**
     * @Author YH
     * @Description //将购物车中的商品提交到订单中（此时商品为未付款的状态）
     * @Date 3:28 PM 10/25/2021
     * @Param [request, session]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/submitOrder")
    public String submitOrder(HttpServletRequest request, HttpSession session){
        // 判断用户是否已经登录，没有登录，订单不能够提交
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login.jsp";
        }
        // 封装一个Order对象传给Service层
        Order order = new Order();
        //随机的订单号
        order.setOid(CommonsUtils.getUUID());
        //下单的时间
        order.setOrderTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()));
        //取出cart中的属性得到总价格
        Cart cart = (Cart) session.getAttribute("cart");
        order.setTotal(cart.getTotal());
        //设置订单的状态未0（表示未付款的状态）
        order.setState(0);
        //收货的地址为空
        order.setAddress(null);
        //收货人为空
        order.setName(null);
        //收货人电话为空
        order.setTelephone(null);
        //改订单绑定当前的用户
        order.setUser(user);

        //这里得到购物车的全部商品项
        Map<String, CartItem> cartItems = cart.getCartItems();
        //将订单中的商品放入order中的OrderItem集合
        for(Map.Entry<String, CartItem> entry : cartItems.entrySet()){
            //取出购物项
            CartItem cartItem = entry.getValue();
            //创建订单集合
            OrderItem orderItem = new OrderItem();
            //1)private String itemId;
            orderItem.setItemId(CommonsUtils.getUUID());
            //2)private int count;
            orderItem.setCount(cartItem.getBuyNum());
            //3)private double subtotal;
            orderItem.setSubtotal(cartItem.getSubTotal());
            //4)private Product product;
            orderItem.setProduct(cartItem.getProductVo());
            //5)private Order order;
            orderItem.setOrder(order);
            //将订单项添加到订单的订单项集合中
            order.getOrderItems().add(orderItem);
        }
        //order对象封装完毕
        //传递数据到service层
        service.submitOrder(order);
        
        session.setAttribute("order", order);
        return "redirect:/order_info.jsp";
    }
    



    @RequestMapping(value = "/myOrders")
    public String myOrders(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //如果没有登录，不能够查看我的订单
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //查询用户的所有订单集合（单表查询orders表）
        List<Order> orderList = service.findAllOrders(user.getUid());
        //但是这样查询，Order对象中的数据是不完整的，缺少List<OrderItem> orderItems数据
        //对订单集合进行遍历，查询每个订单下的所有订单项，为每个订单填充订单项的信息
        if (orderList != null) {
            for (Order order : orderList) {
                //获得每个订单的oid，根据oid去数据库中查询得到订单项信息
                String oid = order.getOid();
                List<OrderItem> orderItemList = service.findAllOrderItemByOid(oid);
                for (OrderItem orderItem : orderItemList) {
                    order.getOrderItems().add(orderItem);
                }
            }
        }
        request.setAttribute("orderList", orderList);
        return "/order_list";
    }


        //根据订单号码id删除
        @RequestMapping(value = "/removeByoid")
        public String removeByoid (HttpServletRequest request, HttpSession session){
            String oid1 = request.getParameter("oid");
            service.deleteOrderByoid(oid1);

            User user = (User) session.getAttribute("user");
            List<Order> orderList = service.findAllOrders(user.getUid());
            //但是这样查询，Order对象中的数据是不完整的，缺少List<OrderItem> orderItems数据
            //对订单集合进行遍历，查询每个订单下的所有订单项，为每个订单填充订单项的信息
            if (orderList != null) {
                for (Order order : orderList) {
                    //获得每个订单的oid，根据oid去数据库中查询得到订单项信息
                    String oid = order.getOid();
                    List<OrderItem> orderItemList = service.findAllOrderItemByOid(oid);
                    for (OrderItem orderItem : orderItemList) {
                        order.getOrderItems().add(orderItem);
                    }
                }
            }
            request.setAttribute("orderList", orderList);


            return "/order_list";
        }


    @RequestMapping(value = "/gogo", method = RequestMethod.GET)
    public String getvalue(HttpServletRequest request, HttpSession session, @RequestParam("name") String name,@RequestParam("address") String address,@RequestParam("telephone") String telephone){
       Order order=(Order) session.getAttribute("order");
        String oid = order.getOid();
        //根据订单号修改address 和name和telephone
        service.updateOrderAddr(address,name,telephone,oid);
        request.setAttribute("msg","商品购买成功");
        return "/order_info";

    }
}





























