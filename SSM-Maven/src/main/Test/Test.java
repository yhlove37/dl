

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ssm.service.ReplyService;
import ssm.service.impl.ReplyServiceImpl;

/**
 * @ClassName Test
 * @Description TODD
 * @Autuor yh139
 * @Date 10/27/2021 12:00 PM
 * @Version 1.0
 **/

public class Test {


  @org.junit.Test
    public void test(){
      ReplyServiceImpl replyService = new ReplyServiceImpl();

      long replyCountByProductId = replyService.getReplyCountByProductId(1);
        System.out.println(replyCountByProductId);


    }
}
