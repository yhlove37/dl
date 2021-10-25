package ssm.service;

//import ssm.entity.Jl;
import ssm.entity.User;
import ssm.vo.ProductVo;

import java.util.List;

public interface UserService {
    boolean register(User user);

    void mailActivate(String activeCode);

    boolean checkUserName(String username);

    User login(String username, String password);


    List<User> findAllusers();

    void deletebyid(String uid);

    void updateaction(User user);

     String activebyuid(String uid);

     String canelbyuid (String uid);

}
