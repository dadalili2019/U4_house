package cn.itcast.web.ui;

import cn.itcast.model.Type;
import cn.itcast.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName TypeController
 * @Date 2019/12/27 8:45
 * @Version 1.0
 */
//房屋类型web
@Controller
@RequestMapping("/page/")
public class TypeController {


    @Autowired
    private TypeService typeService;

    /**
     * 查询所有户型类型
     * @return
     */
    @RequestMapping("findTypeAll.do")
    @ResponseBody
    private List<Type> findTypeAll(){
        return typeService.findAll();
    }
}
