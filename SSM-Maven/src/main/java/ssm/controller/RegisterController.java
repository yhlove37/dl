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
import java.util.Random;


@RequestMapping()
@Controller
public class RegisterController {
    @Autowired
    UserService service;

    @Autowired
    CommonsService sendMailService;

    /**
     * @return java.lang.String
     * @Author YH
     * @Description //注册跳转接受验证信息，用于完成用户的激活
     * @Date 10:37 AM 10/26/2021
     * @Param [request, user, response]
     **/
    @RequestMapping(value = "/register/do", method = RequestMethod.POST)
    public String doRegister(HttpServletRequest request, @ModelAttribute User user, HttpServletResponse response) throws UnsupportedEncodingException {
        String email = request.getParameter("email");
        //补全信息，默认为空
        user.setState(0);
        //设置激活码
        Random random = new Random(9000);
        int i = random.nextInt(9000) + 1000;
        System.out.println(i);
        String activeCode = i + "";
        user.setCode(activeCode);
        //将user传递到service层
        boolean isRegisterSuccess = service.register(user);
        //是否注册成功
        if (isRegisterSuccess) {
            String emailMsg = "恭喜您注册成功！你的激活码：" + activeCode + "";
            sendMailService.sendActivateMail(user.getEmail(), emailMsg);
            request.setAttribute("activeCodes", activeCode);
            request.setAttribute("email", email);
            //跳转到邮箱激活页面
            return "/identycodes";
        } else {
            //注册失败
            return "redirect:registerFail.jsp";
        }
    }

    /**
     * @return java.lang.String
     * @Author YH
     * @Description // 完成激活码的验证
     * @Date 10:46 AM 10/26/2021
     * @Param [request, redirectAttributes]
     **/
    @RequestMapping(value = "/register/active", method = RequestMethod.GET)
    public String doActivate(HttpServletRequest request) {
        String activecodes = request.getParameter("activecodes");
        String codes = request.getParameter("codes");
        if (codes != null && activecodes.equals(codes)) {
            service.mailActivate(codes);
            return "redirect:/login.jsp";
        } else return "redirect:/registerFail.jsp ";


    }


    /**
     * @Author YH
     * @Description // 用于ajax校验用户名是否存在
     * @Date 10:55 AM 10/26/2021
     * @Param [request]
     * @return java.lang.String
     **/

    @ResponseBody     //返回json数据类型而不是视图层，需要次注解
    @RequestMapping("/register/checkUsername")
    public String doCheckUserName(HttpServletRequest request) {

        String userName = request.getParameter("username");
        boolean isExist = service.checkUserName(userName);
        //     isExist 查询到则为真 否则则为假 json数据格式
        String json = "{\"isExist\":" + isExist + "}";
        return json;
    }

}
