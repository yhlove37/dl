package ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.dao.UserMapper;
//import ssm.entity.Jl;
import ssm.entity.User;
import ssm.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserMapper mapper;
    
    //注册
    @Override
    public boolean register(User user) {
        int row = 0;
        row = mapper.register(user);
        //是否注册成功
        return row>0;
    }
    
    //激活用户
    @Override
    public void mailActivate(String activeCode) {
        
        mapper.mailActivate(activeCode);
    }

    //检测用户姓名是否已存在
    @Override
    public boolean checkUserName(String username) {
        Long isExist = 0L;
        isExist = mapper.checkUserName(username);
        return isExist>0;
    }

    @Override
    public User login(String username, String password) {
        return mapper.login(username, password);
    }

    @Override
    public List<User> findAllusers() {
        List<User> users = mapper.findAllusers();
        System.out.println("打出所有用户"+users);
        return users;
    }

    @Override
    public void deletebyid(String uid) {
        mapper.deletebyid(uid);
    }

    @Override
    public void updateaction(User user) {
        mapper.updateaction(user);
    }

//    @Override
//    public void addjl(Jl jl) {
//        mapper.addjl(jl);
//    }

    @Override
    public String activebyuid(String uid) {
        mapper.activebyuid(uid);
        String mes="激活成功";
        return  mes;
    }

    @Override
    public String canelbyuid(String uid) {
        mapper.canelbyuid(uid);
        String mes="取消激活成功";
        return mes;
    }
}
