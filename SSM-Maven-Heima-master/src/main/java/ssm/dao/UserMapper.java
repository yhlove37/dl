package ssm.dao;

        import org.apache.ibatis.annotations.Param;

        import ssm.entity.User;

        import java.util.List;

public interface UserMapper {
    int register(User user);

    void mailActivate(@Param("activeCode") String activeCode);

    Long checkUserName(@Param("username") String username);

    User login(@Param("username") String username,@Param("password") String password);

    List<User> findAllusers();

    void deletebyid(String uid);

//修改用户
    void updateaction(User user);

    void activebyuid(String uid);

    void canelbyuid(String uid);
}
