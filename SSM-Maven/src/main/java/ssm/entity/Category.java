package ssm.entity;

import java.io.Serializable;

//存入redis的pojo类必须实现Serializable
//商品的分类
public class Category implements Serializable {
    private String cid;
    private String cname;
    private int   cstate;
    private String cinfo;

    public Category() {
    }

    public Category(String cid, String cname, int cstate, String cinfo) {
        this.cid = cid;
        this.cname = cname;
        this.cstate = cstate;
        this.cinfo = cinfo;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCstate() {
        return cstate;
    }

    public void setCstate(int cstate) {
        this.cstate = cstate;
    }

    public String getCinfo() {
        return cinfo;
    }

    public void setCinfo(String cinfo) {
        this.cinfo = cinfo;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", cstate=" + cstate +
                ", cinfo='" + cinfo + '\'' +
                '}';
    }
}
