package ssm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ssm.entity.User;
import ssm.service.CommonsService;
import ssm.service.UserService;
import ssm.utils.CommonsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


@RequestMapping()
@Controller
public class RegisterController {
    @Autowired
    UserService service;

    @Autowired
    CommonsService sendMailService;
    
    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public String doRegister(HttpServletRequest request, @ModelAttribute User user, HttpServletResponse response) throws UnsupportedEncodingException {
        //防止中文乱码
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        //补全对应的信息
        user.setHeadimage(null);
        user.setUid(CommonsUtils.getUUID());
        user.setTelephone(null);
        user.setState(0);
        //设置激活码
        String activeCode = CommonsUtils.getUUID();
        user.setCode(activeCode);
        //将user传递到service层
        boolean isRegisterSuccess = service.register(user);
        //是否注册成功
        if(isRegisterSuccess){
            String emailMsg = "恭喜您注册成功！你的激活码："+activeCode+"";
            sendMailService.sendActivateMail(user.getEmail(),emailMsg);
            request.setAttribute("activeCodes",activeCode);
            request.setAttribute("email",email);
            //跳转到邮箱激活页面
            return "/identycodes";
        } else {
            //注册失败
            return "redirect:registerFail.jsp";
        }
    }
    
    @RequestMapping(value = "/register/active", method = RequestMethod.GET)
    public String doActivate(HttpServletRequest request,RedirectAttributes redirectAttributes){
        String activecodes = request.getParameter("activecodes");
        String codes = request.getParameter("codes");
        if (codes!=null&&activecodes.equals(codes)){
            //如果激活码对应
            service.mailActivate(codes);
            return "redirect:/login.jsp";
        }
        else  return "redirect:/registerFail.jsp ";


    }
    
    @ResponseBody         //返回json，需要此注解
    @RequestMapping("/register/checkUsername")
    public String doCheckUserName(HttpServletRequest request){
        
        String userName = request.getParameter("username");
        boolean isExist =  service.checkUserName(userName);
 //     isExist 查询到则为真 否则则为假
        String json = "{\"isExist\":" + isExist + "}";
        return json;
    }
    
}
