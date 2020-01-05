package cn.itcast.web.admin;

import cn.itcast.model.Users;
import cn.itcast.service.UserService;
import cn.itcast.utils.UserCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName UserController
 * @Date 2019/12/25 23:55
 * @Version 1.0
 */
//用户web
@RestController(value = "adminUserController")
@RequestMapping("/admin/")  //指定请求前缀
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 动态查询用户+分页
     * @param userCondition
     * @return
     */
    @RequestMapping("getUserData.do")
    public Map<String,Object> getUserData(UserCondition userCondition){
        PageInfo<Users> pageInfo=userService.getUserByPage(userCondition);
        //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
