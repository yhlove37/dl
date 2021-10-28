package ssm.service;

import ssm.entity.Reply;
import ssm.entity.User;
import ssm.utils.Page;

import java.util.List;

public interface ReplyService {

    int getReplyCountByProductId(int  productId);

    List<Reply> selReplyByProductId(int productId, Page page);

    User getUserById(int uid);

    void insertReply(Reply reply);
}
