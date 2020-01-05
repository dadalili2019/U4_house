package cn.itcast.web.ui;

import cn.itcast.model.Users;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
