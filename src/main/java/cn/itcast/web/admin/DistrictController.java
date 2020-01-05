package cn.itcast.web.admin;

import cn.itcast.model.District;
import cn.itcast.service.DistrictService;
import cn.itcast.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author caoqian
 * @Date 2019/12/20 19:33
 * @Version 1.0
 */
//区域管理web
@RestController
@RequestMapping("/admin/")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    /**
     * 分页查询区域数据
     *
     * @param pageUtil
     * @return
     */
    @RequestMapping("getAllByPage.do")
    public Map<String, Object> getAllByPage(PageUtil pageUtil) {
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<District> pageInfo = districtService.findAllByPage(pageUtil);

        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;

    }

    /**
     * 新增区域信息
     *
     * @param district
     * @return
     */
    @RequestMapping("addDistrict.do")
    public String addDistrict(District district) {
        try {
            //调用业务
            int result = districtService.addDistrict(district);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":" + result + "}";   //拼接的json
        } catch (Exception e) {
            return "{\"result\":-1}";
        }

    }

    /**
     * 修改信息回显
     *
     * @param id
     * @return
     */
    @RequestMapping("findById.do")
    public District findById(Integer id) {
        District district = districtService.findById(id);
        return district;
    }

    /**
     * 修改区域信息
     *
     * @param district
     * @return
     */
    @RequestMapping("updateDistrict.do")
    public String updateDistrict(District district) {
        try {
            //调用业务
            districtService.updateDistrict(district);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":" + 1 + "}";   //拼接的json
        } catch (Exception e) {
            return "{\"result\":-1}";
        }
    }


    /**
     * 根据id删除区域信息
     *
     * @param id
     * @return
     */
    @RequestMapping("delDistrictById.do")
    public String delDistrictById(Integer id) {
        try {
            //调用业务
            districtService.delById(id);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":" + 1 + "}";   //拼接的json
        } catch (Exception e) {
            return "{\"result\":-1}";
        }
    }

    //批量删除区域
    //前台传递数据的格式:ids=1,2,3,4     后台: String ids变量接收数据
    //前台传递数据的格式:ids=1&ids=2&ids=3     后台: Integer []ids变量接收数据
    @RequestMapping("deleteMoreDistrict.do")
    public String deleteMoreDistrict(String ids) {
        try {
            String[] split = ids.split(",");
            Integer[] integers = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                integers[i] = new Integer(split[i]);
            }
            int temp = districtService.deleteMoreDistrict(integers);
            return "{\"result\":" + temp + "}";   //拼接的json
        } catch (Exception e) {
            return "{\"result\":-1}";
        }
    }
}
