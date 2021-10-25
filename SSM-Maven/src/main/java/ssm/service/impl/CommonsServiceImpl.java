package ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ssm.service.CommonsService;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class CommonsServiceImpl implements CommonsService {
    
    @Autowired   //完成bean的自动装配
    JavaMailSender mailSender;

    //发送注册邮件业务
    @Override
    public void sendActivateMail(String toMail, String emailMsg) {
//        spring 自带的这侧邮件业务 mailSender
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

        try {
            messageHelper.setTo(toMail);
            messageHelper.setSubject("用户注册");
            //设置发送者
            messageHelper.setFrom(new InternetAddress("1402527192@qq.com"));
            messageHelper.setText(emailMsg, true);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
