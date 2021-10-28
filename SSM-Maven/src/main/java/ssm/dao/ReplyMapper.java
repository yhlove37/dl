package ssm.dao;

import org.apache.ibatis.annotations.Param;
import ssm.entity.Reply;
import ssm.entity.User;
import ssm.utils.Page;

import java.util.List;

public interface ReplyMapper {

    int selReplyCountByProductId(int productId);

//   获取回复的内容
    List<Reply> selReplyByProductId(@Param("productId") int productId,@Param("page") Page page);
   //根据用户用户id查找对应的信息
    User selUserByUserId(@Param("uid") int uid);
    //插入回复信息
    void insertReply(Reply reply);
}
