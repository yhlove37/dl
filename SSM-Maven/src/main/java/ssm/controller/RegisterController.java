package ssm.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ssm.entity.User;
import ssm.service.CommonsService;
import ssm.service.UserService;
import ssm.utils.CommonsUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RequestMapping()
@Controller
public class RegisterController {
    @Autowired
    UserService service;

    @Autowired
    CommonsService sendMailService;
    
    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public String doRegister(HttpServletRequest request) throws UnsupportedEncodingException {
        String email = request.getParameter("email");
        request.setCharacterEncoding("UTF-8");
        //获取表单信息
        Map<String, String[]> properties = request.getParameterMap();
        User user = new User();
        try {
            // 所有的这一切只是为了注册 防止不识别
            ConvertUtils.register(new Converter() {
                @Override
                public Object convert(Class aClass, Object o) {

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse = null;
                    try {
                        parse = format.parse(o.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return parse;
                }

            }, Date.class);
            //user 与前端对应的数据一一对应
            BeanUtils.populate(user,properties);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } 
        
        //uid
        user.setUid(CommonsUtils.getUUID());
        //TELEPHONE
        user.setTelephone(null);
        //  int state 
        user.setState(0);
        // code 激活码
        String activeCode = CommonsUtils.getUUID();
        user.setCode(activeCode);
        
        //将user传递到service层
        boolean isRegisterSuccess = service.register(user);
        
        //是否注册成功
        if(isRegisterSuccess){
            //发送激活邮件
            //线上激活不了  不知道为什么
//            String emailMsg = "恭喜您注册成功！请点击下面的链接激活您的账户"
//                    + "<a href='http://localhost:8080/admin/register/active?activeCode=" + activeCode + "'>"
//                    + "点击激活，你的激活码：" + activeCode + "</a>";
            String emailMsg = "恭喜您注册成功！你的激活码："+activeCode+"";
                    ;
            //调用sercice层
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
        System.out.println("打印激活码"+activecodes);
        String codes = request.getParameter("codes");
        System.out.println("打印输入的激活码"+codes);
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
