package cn.itcast.service;

import cn.itcast.model.House;
import cn.itcast.utils.PageUtil;
import cn.itcast.utils.SearchHouseCondition;
import com.github.pagehelper.PageInfo;

/**
 * @Author caoqian
 * @ClassName HouseService
 * @Date 2019/12/27 15:17
 * @Version 1.0
 */
//房屋出租service
public interface HouseService {


    /**
     * 添加房屋信息
     * @param house
     */
    int addHouse(House house);

    /**
     * 分页房屋信息
     * @return
     */
    PageInfo<House> getAllHouseByPage(Integer uid, PageUtil pageUtil);

    /**
     * 修改房屋信息回显
     * @param id
     * @return
     */
    House findById(String id);

    /**
     * 修改房屋信息
     * @param house
     */
    int updateHouse(House house);

    /**
     * 删除房屋信息
     * @param id 房屋id
     * @param isDel 是否删除逻辑参数
     */
    void updateIsPass(String id, String isDel);


    /**
     * 根据条件查询所有房屋信息(已完成审核+用户未自行删除)
     * @param searchHouseCondition
     * @param pageUtil
     * @return
     */
    PageInfo<House> getUiAllHouseByPage(SearchHouseCondition searchHouseCondition, PageUtil pageUtil);

    /**
     * 根据房屋id查询房屋详细信息
     * @param id
     * @return
     */
    House findUserHouseById(String id);
}
