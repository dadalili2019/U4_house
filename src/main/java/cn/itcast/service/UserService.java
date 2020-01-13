package cn.itcast.service;

import cn.itcast.model.UserHouseMsg;
import cn.itcast.model.Users;
import cn.itcast.utils.UserCondition;
import com.github.pagehelper.PageInfo;

/**
 * @Author caoqian
 * @ClassName UserService
 * @Date 2019/12/25 23:55
 * @Version 1.0
 */
//用户service
public interface UserService {
    /**
     * 分页动态查询用户数据
     * @param userCondition
     * @return
     */
    PageInfo<Users> getUserByPage(UserCondition userCondition);


    /**
     * 验证用户名是否存在
     * @param username
     * @return
     */
    boolean checkUserName(String username);

    /**
     * 注册用户
     * @param users
     * @return
     */
    int regsUser(Users users);

    /**
     * 登录用户
     * @param users
     * @return
     */
    Users loginUser(Users users);

    /**
     * 根据用户id查询房屋详细信息
     * @param userHouseMsg
     * @return
     */
    Users getHouseMsgByID(UserHouseMsg userHouseMsg);

    /**
     * 前台验证用户名是否存在
     * @param username
     * @return
     */
    Users checkUser(String username);


    /**
     * 前台用户验证旧密码是否正确
     * @param oldPassword
     * @return
     */
    int checkOldPassword(String oldPassword,String username);

    /**
     * 用户修改个人信息
     * @param users
     * @return
     */
    int modifyMsg(Users users);
}
