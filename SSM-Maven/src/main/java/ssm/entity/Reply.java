package ssm.entity;

import java.io.Serializable;

/**
 * @ClassName Reply
 * @Description TODD
 * @Autuor yh139
 * @Date 10/27/2021 11:20 AM
 * @Version 1.0
 **/

public class Reply implements Serializable {
    private  int reply_id;
    private  int user_id;
    private  int product_id;
    private String reply_createTime;
    private String reply_modifyTime;
    private String reply_content;
    private User user;
    private Product product;


    public Reply() {
    }

    public Reply(int reply_id, int user_id, int product_id, String reply_createTime, String reply_modifyTime, String reply_content, User user, Product product) {
        this.reply_id = reply_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.reply_createTime = reply_createTime;
        this.reply_modifyTime = reply_modifyTime;
        this.reply_content = reply_content;
        this.user = user;
        this.product = product;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getReply_createTime() {
        return reply_createTime;
    }

    public void setReply_createTime(String reply_createTime) {
        this.reply_createTime = reply_createTime;
    }

    public String getReply_modifyTime() {
        return reply_modifyTime;
    }

    public void setReply_modifyTime(String reply_modifyTime) {
        this.reply_modifyTime = reply_modifyTime;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "reply_id=" + reply_id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", reply_createTime='" + reply_createTime + '\'' +
                ", reply_modifyTime='" + reply_modifyTime + '\'' +
                ", reply_content='" + reply_content + '\'' +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
