package cn.itcast.model;

import java.io.Serializable;

/**
 * @Author caoqian
 * @ClassName UserHouseMsg
 * @Date 2020/1/11 16:30
 * @Version 1.0
 */
//用户id和房屋id pojo
public class UserHouseMsg implements Serializable {
    
    private String uid;//用户id
    private String hid;//房屋id

    public UserHouseMsg(String uid, String hid) {
        this.uid = uid;
        this.hid = hid;
    }

    public UserHouseMsg() {
    }

    public String getId() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    @Override
    public String toString() {
        return "UserHouseMsg{" +
                "uid=" + uid +
                ", hid=" + hid +
                '}';
    }
}
