package cn.itcast.web.admin;

import cn.itcast.model.House;
import cn.itcast.service.AdminHouseService;
import cn.itcast.service.DistrictService;
import cn.itcast.service.HouseService;
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
//房屋审核web
@RestController
@RequestMapping("/admin/")
public class AdminHouseController {

    @Autowired
    private AdminHouseService adminHouseService;

    /**
     * 分页房屋查询数据
     *
     * @param pageUtil
     * @return
     */
    @RequestMapping("getAllHouseByPage.do")
    public Map<String, Object> getAllHouseByPage(PageUtil pageUtil) {
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<House> pageInfo = adminHouseService.findAllByPage(pageUtil);

        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;

    }

    /**
     * 新增房屋信息
     *
     * @param house
     * @return
     */
    @RequestMapping("addHouse.do")
    public String addDistrict(House house) {
        try {
            //调用业务
            int result = adminHouseService.addHouse(house);
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
    @RequestMapping("findHouseById.do")
    public House findById(Integer id) {
        House house = adminHouseService.findById(id);
        return house;
    }

    /**
     * 修改房屋信息
     *
     * @param house
     * @return
     */
    @RequestMapping("updateHouse.do")
    public String updateHouse(House house) {
        try {
            //调用业务
            adminHouseService.updateHouse(house);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":" + 1 + "}";   //拼接的json
        } catch (Exception e) {
            return "{\"result\":-1}";
        }
    }


//    /**
//     * 根据id删除房屋信息
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("delHouseById.do")
//    public String delHouseById(Integer id) {
//        try {
//            //调用业务
//            adminHouseService.delById(id);
//            //封装返回数据
//            // Map<String,Object> map=new HashMap<>();   //自动转json
//            // map.put("result",result);
//            return "{\"result\":" + 1 + "}";   //拼接的json
//        } catch (Exception e) {
//            return "{\"result\":-1}";
//        }
//    }

//    //批量删除区域
//    //前台传递数据的格式:ids=1,2,3,4     后台: String ids变量接收数据
//    //前台传递数据的格式:ids=1&ids=2&ids=3     后台: Integer []ids变量接收数据
//    @RequestMapping("deleteMoreHouse.do")
//    public String deleteMoreHouse(String ids) {
//        try {
//            String[] split = ids.split(",");
//            Integer[] integers = new Integer[split.length];
//            for (int i = 0; i < split.length; i++) {
//                integers[i] = new Integer(split[i]);
//            }
//            int temp = adminHouseService.deleteMoreHouse(integers);
//            return "{\"result\":" + temp + "}";   //拼接的json
//        } catch (Exception e) {
//            return "{\"result\":-1}";
//        }
//    }
}
