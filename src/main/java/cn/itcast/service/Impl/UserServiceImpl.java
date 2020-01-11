package cn.itcast.service.Impl;

import cn.itcast.dao.TypeMapper;
import cn.itcast.dao.UsersMapper;
import cn.itcast.model.*;
import cn.itcast.service.UserService;
import cn.itcast.utils.MD5Utils;
import cn.itcast.utils.UserCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName UserServiceImpl
 * @Date 2019/12/25 23:56
 * @Version 1.0
 */
//用户service
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;


    /**
     * 动态查询用户信息+分页
     *
     * @param userCondition
     * @return
     */
    @Override
    public PageInfo<Users> getUserByPage(UserCondition userCondition) {
        PageHelper.startPage(userCondition.getPage(), userCondition.getRows());
        //封装条件
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if (userCondition.getName() != null && userCondition.getName() != "") {
            criteria.andNameLike("%" + userCondition.getName() + "%");
        }
        if (userCondition.getTel() != null && userCondition.getTel() != "") {
            criteria.andTelephoneLike("%" + userCondition.getTel() + "%");
        }
        List<Users> userList = usersMapper.selectByExample(usersExample);
        return new PageInfo<>(userList);
    }

    /**
     * 验证用户名是否存在
     *
     * @param username
     * @return
     */
    @Override
    public boolean checkUserName(String username) {
        List<Users> users = usersMapper.checkUserName(username);
        if (users != null && users.size() != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 用户注册
     *
     * @param users
     * @return
     */
    @Override
    public int regsUser(Users users) {
        //对密码进行加密，使用MD5
        String newPassword = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(newPassword);
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }

    /**
     * 用户登录
     *
     * @param users
     * @return
     */
    @Override
    public Users loginUser(Users users) {
        String newPassword = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(newPassword);
        return usersMapper.findUser(users);
    }

    /**
     * 根据用户id查询房屋详细信息
     * @param userHouseMsg
     * @return
     */
    @Override
    public Users getHouseMsgByID(UserHouseMsg userHouseMsg) {
        Users users = usersMapper.getHouseMsgById(userHouseMsg);
        return users;
    }
}
