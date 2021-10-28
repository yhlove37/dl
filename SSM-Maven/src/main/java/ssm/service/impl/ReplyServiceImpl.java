package ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.dao.ProductMapper;
import ssm.dao.ReplyMapper;
import ssm.entity.Reply;
import ssm.entity.User;
import ssm.service.ReplyService;
import ssm.utils.Page;

import java.util.List;

/**
 * @ClassName ReplyServiceImpl
 * @Description TODD
 * @Autuor yh139
 * @Date 10/27/2021 11:41 AM
 * @Version 1.0
 **/
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    @Override
    public int getReplyCountByProductId(int productId) {

        int replyCount = replyMapper.selReplyCountByProductId(productId);
        return replyCount;

    }

    @Override
    public List<Reply> selReplyByProductId(int productId, Page page) {
        List<Reply> replyList = replyMapper.selReplyByProductId(productId,page);
        if (replyList != null){
            return  replyList;
        }
        return null;
    }

    @Override
    public User getUserById(int uid) {
        User user = null;
        user = replyMapper.selUserByUserId(uid);
        return user;
    }

    @Override
    public void insertReply(Reply reply) {
        replyMapper.insertReply(reply);
    }
}
