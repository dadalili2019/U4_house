package cn.itcast.web.ui;

import cn.itcast.model.Street;
import cn.itcast.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName StreetController
 * @Date 2019/12/27 14:41
 * @Version 1.0
 */
@RequestMapping("/page/")
@Controller
public class StreetController {

    @Autowired
    private StreetService streetService;

    /**
     * 根据id查询街道信息
     * @return
     */
    @RequestMapping("findStreetById.do")
    @ResponseBody
    private List<Street> findStreetById(Integer did){
       return streetService.findById(did);
    }
}
