package cn.itcast.web.ui;

import cn.itcast.model.Users;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author caoqian
 * @ClassName UiUserController
 * @Date 2019/12/26 9:08
 * @Version 1.0
 */
//用户web
@Controller(value = "uiUserController")
@RequestMapping("/page/")
public class UiUserController {


    @Autowired
    private UserService userService;

    /**
     * 验证用户名是否存在
     *
     * @param uname
     * @return
     */
    @RequestMapping("checkUserName.do")
    @ResponseBody
    public String checkUserName(String uname) {
        boolean result = userService.checkUserName(uname);
        if (result) {
            return "{\"result\":" + result + "}";
        }
        return "{\"result\":" + false + "}";
    }

    /**
     * 用户注册
     *
     * @param users
     * @return
     */
    @RequestMapping("regUser.do")
    public String regUser(Users users) {
        int result = userService.regsUser(users);
        if (result > 0) {
            return "redirect:login.jsp";
        } else {
            return "redirect:error.html";
        }
    }

    /**
     * 用户登录
     *
     * @param users
     * @param session
     * @return
     */
    @RequestMapping("login.do")
    public ModelAndView login(Users users, HttpSession session) {
        Users result = userService.loginUser(users);
        ModelAndView modelAndView = new ModelAndView();
        if (result != null) {
            modelAndView.setViewName("forward:showHouse.do");
            session.setAttribute("user", result);
        } else {
            modelAndView.setViewName("redirect:error.jsp");
        }
        return modelAndView;
    }


    /**
     * 用户退出
     * @param session
     * @param userName
     * @return
     */
    @RequestMapping("UserGoOut.do")
    public String UserGoOut(HttpSession session,String userName){
        session.removeAttribute(userName);
        return "redirect:out.jsp";

    }

    /**
     * 修改用户信息-异步查询用户名是否存在
     * @param username
     * @return
     */
    @RequestMapping("checkUser.do")
    @ResponseBody
    public Users checkUser(String username){
        Users users=userService.checkUser(username);
            if (users!=null) {
                return users;
            }else {
                return null;
            }
    }

    /**
     * 查询旧密码是否正确
     * @param oldPassword
     * @return
     */
    @RequestMapping("checkOldPassword.do")
    @ResponseBody
    public String checkOldPassword(String oldPassword,String username){
        try {
            //调用业务
            int result=userService.checkOldPassword(oldPassword,username);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":" + 1 + "}";   //拼接的json
        } catch (Exception e) {
            return "{\"result\":-1}";
        }
    }

    /**
     * 用户修改个人信息
     * @param users
     * @return
     */
    @RequestMapping("modifyUser.do")
    public String modifyUser(Users users,HttpSession session){
        try {
            Users users1 = (Users) session.getAttribute("user");
            Integer id = users1.getId();
            users.setId(id);
            System.out.println(id);
            int result=userService.modifyMsg(users);
            return "forward:login.jsp";
        }catch (Exception e){
            return "forward:error.jsp";
        }
    }
}
