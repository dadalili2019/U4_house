package cn.itcast.utils;

/**
 * @Author caoqian
 * @ClassName UserCondition
 * @Date 2019/12/25 23:58
 * @Version 1.0
 */
//动态查询用户pojo
public class UserCondition extends PageUtil{
    //封装的查询条件
    private String name;
    private String tel;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
