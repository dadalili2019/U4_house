package cn.itcast.web.admin;

import cn.itcast.model.House;
import cn.itcast.model.UserHouseMsg;
import cn.itcast.model.Users;
import cn.itcast.service.AdminHouseService;
import cn.itcast.service.DistrictService;
import cn.itcast.service.HouseService;
import cn.itcast.service.UserService;
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

    @Autowired
    private UserService userService;

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

    /**
     * 根据用户id查询房屋详细信息
     *
     * @param uid+hid
     * @return
     */
    @RequestMapping("getHouseById.do")
    public Users getHouseById(String uid, String hid) {
        UserHouseMsg userHouseMsg = new UserHouseMsg();
        userHouseMsg.setId(uid);
        userHouseMsg.setHid(hid);
        Users users = userService.getHouseMsgByID(userHouseMsg);
        return users;
    }

    /**
     * 根据id确认审核房屋信息
     *
     * @param id
     * @return
     */
    @RequestMapping("goPassById.do")
    public String goPassById(String id) {
        try {
            //调用业务
            int result = adminHouseService.PasHouseById(id);
            //封装返回数据
            // Map<String,Object> map=new HashMap<>();   //自动转json
            // map.put("result",result);
            return "{\"result\":" + 1 + "}";   //拼接的json
        } catch (Exception e) {
            return "{\"result\":-1}";
        }
    }

    /**
     * 查询已审核通过的房屋信息
     *
     * @param pageUtil
     * @return
     */
    @RequestMapping("getAllHouseByPageIsPass.do")
    public Map<String, Object> getAllHouseByPageIsPass(PageUtil pageUtil) {
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<House> pageInfo = adminHouseService.getAllHouseByPageIsPass(pageUtil);
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;

    }

    /**
     * 查询未审核通过的房屋信息
     *
     * @param pageUtil
     * @return
     */
    @RequestMapping("getAllHouseByPageIsNotPass.do")
    public Map<String, Object> getAllHouseByPageIsNotPass(PageUtil pageUtil) {
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<House> pageInfo = adminHouseService.getAllHouseByPageIsNotPass(pageUtil);
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }
}
