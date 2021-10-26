package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ssm.entity.User;
import ssm.service.ProductService;
import ssm.service.UserService;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    ProductService service;


    //修改用户信息
    @RequestMapping(value = "/touserupdate")
    public String userupdate(HttpServletRequest request, HttpSession session,HttpServletResponse response) throws ParseException {
        User user = (User) session.getAttribute("user");

        if(user==null){
            return "redirect:/user/login";
        }

        return "userupdate";

    }

    @RequestMapping(value = "/updateaction")
    public String updateaction(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpSession session, @RequestParam(value = "headfile",required = false) CommonsMultipartFile headfile,HttpServletRequest request) throws IOException {
        System.out.println(headfile.isEmpty());
               // 正是项目RealPath部署后在服务器上的路径
        if (!headfile.isEmpty()){
            String path = request.getServletContext().getRealPath("/upload/head");
            System.out.println(path);
            File realPath = new File(path);
            if (!realPath.exists()){
                realPath.mkdir();
            }
            //上传文件地址
            System.out.println("上传文件保存地址："+realPath);
            headfile.transferTo(new File(realPath +"/"+ headfile.getOriginalFilename()));

//        //本地地址
            String path2="C:\\Users\\yh139\\Desktop\\p3=\\SSM-Maven\\SSM-Maven\\src\\main\\webapp\\upload\\head";
            File realPath2 = new File(path2);
            if (!realPath2.exists()){
                realPath2.mkdir();
            }
            System.out.println("上传文件保存地址："+realPath2);
            headfile.transferTo(new File(realPath2 +"/"+ headfile.getOriginalFilename()));
            String headfiles ="upload/head/"+headfile.getOriginalFilename();
            user.setHeadimage(headfiles);
        }else {
            String headfiles =null;
            //地址持久化
            user.setHeadimage(headfiles);
        }
        userService.updateaction(user);
        session.removeAttribute("user");
        session.removeAttribute("loginError");
        //重定向也可以传参数吗？
        redirectAttributes.addFlashAttribute("cdinfo","修改成功，请重新登录");
        return "redirect:/index";

    }





    //登录
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //如何对密码加密？ MD5算法
        User user = userService.login(username, password);

        if (user==null){
            session.setAttribute("loginError", "账户密码错误");
            return "redirect:/login.jsp";
        }

        //判断用户是否登录成功 user是否是null
        if(user!=null&&user.getState()==3||user.getState()==1){
            //登录成功
            // 判断用户是否勾选了自动登录
            String autoLogin = request.getParameter("autoLogin");
            if("true".equals(autoLogin)){
                //需要自动登录，使用cookie
                Cookie cookie_username = new Cookie("cookie_username", user.getUsername());
                Cookie cookie_password = new Cookie("cookie_password", user.getPassword());
                cookie_password.setMaxAge(10*60);
                
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);
            }
            session.setAttribute("user", user);

//  return "redirect:"+ request.getContextPath() +"/index2.jsp";
            return "redirect:/index";
        } else {
            redirectAttributes.addFlashAttribute("loginError","用户权限不够");
            session.setAttribute("loginError", "用户的权限不够");
            return "redirect:/login.jsp";
        }
    }
    
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginError");
        session.removeAttribute("user");
        return "redirect:/index";
    }

    @RequestMapping(value = "/findAllusers")
    public String findAllusers(HttpServletRequest request, HttpSession session){
        List<User> users = userService.findAllusers();
        request.getSession().setAttribute("users",users);
        return "redirect:/admin/user/list.jsp";
    }


    @RequestMapping(value = "/deletebyid")
    public String deletebyid(HttpServletRequest request, HttpSession session){
        String uid = request.getParameter("uid");

        userService.deletebyid(uid);
        return "forward:findAllusers";
    }


    @RequestMapping(value = "/activebyuid")
    public String activebyuid(HttpServletRequest request, HttpSession session){
        String uid = request.getParameter("uid");
        String type = request.getParameter("type");
        if (type.equals("0")){
            String canelbyuid = userService.canelbyuid(uid);
            request.setAttribute("mes",canelbyuid);

        }else {
            String activebyuid = userService.activebyuid(uid);
            request.setAttribute("mes",activebyuid);
        }

        return "forward:findAllusers";
    }






}
