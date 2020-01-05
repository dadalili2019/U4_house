package cn.itcast.web.ui;

import cn.itcast.model.District;
import cn.itcast.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName DistController
 * @Date 2019/12/27 14:20
 * @Version 1.0
 */
//前台街道信息web
@RequestMapping("/page/")
@Controller(value = "distController")
public class DistController {

    @Autowired
    private DistrictService districtService;

    /**
     * 查询所有区域信息
     * @return
     */
    @RequestMapping("findDistrictAll.do")
    @ResponseBody
    public List<District> findDistrictAll(){
        List<District> districtList=districtService.findAll();
        return districtList;
    }
}
