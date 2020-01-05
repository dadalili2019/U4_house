package cn.itcast.service;

import cn.itcast.model.House;
import cn.itcast.utils.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName AdminHouseService
 * @Date 2019/12/31 16:30
 * @Version 1.0
 */
public interface AdminHouseService {


    /**
     * 分页展示区域信息
     * @param pageUtil
     * @return
     */
    PageInfo<House> findAllByPage(PageUtil pageUtil);

    /**
     * 新增区域信息
     * @param house
     * @return
     */
    int addHouse(House house);

    /**
     * 根据id查询区域信息回显
     * @param id
     * @return
     */
    House findById(Integer id);

    /**
     * 更新区域数据
     * @param house
     * @return
     */
    void updateHouse(House house);

//    /**
//     * 根据id删除区域信息
//     * @param id
//     */
//    void delById(Integer id);
//
//    /**
//     * 批量删除区域信息
//     * @param integers
//     * @return
//     */
//    int deleteMoreHouse(Integer[] integers);

    /**
     * 查询所有区域信息
     * @return
     */
    List<House> findAll();


}
