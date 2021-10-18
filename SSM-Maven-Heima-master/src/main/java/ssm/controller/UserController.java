package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ssm.entity.User;
import ssm.service.ProductService;
import ssm.service.UserService;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String userupdate(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if(user==null){
            return "redirect:/user/login";
        }
            request.setAttribute("userinfo",user);

            return "redirect:/userupdate.jsp";


    }

    @RequestMapping(value = "/updateaction")
    public String updateaction(@ModelAttribute User user,RedirectAttributes redirectAttributes, HttpSession session,Model model) {

        userService.updateaction(user);
        session.removeAttribute("user");
        //重定向也可以传参数吗？
        redirectAttributes.addFlashAttribute("cdinfo","修改成功，请重新登录");
        //这还是在服务内，所以本质是转发
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
            return "redirect:/index.jsp";
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
            System.out.println("++++++++++++++++"+uid);
            String activebyuid = userService.activebyuid(uid);
            request.setAttribute("mes",activebyuid);
        }



        return "forward:findAllusers";
    }






}
