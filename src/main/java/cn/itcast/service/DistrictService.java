package cn.itcast.service;

import cn.itcast.model.District;
import cn.itcast.utils.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author caoqian
 * @Date 2019/12/20 19:35
 * @Version 1.0
 */
public interface DistrictService {

    /**
     * 分页展示区域信息
     * @param pageUtil
     * @return
     */
    PageInfo<District> findAllByPage(PageUtil pageUtil);

    /**
     * 新增区域信息
     * @param district
     * @return
     */
    int addDistrict(District district);

    /**
     * 根据id查询区域信息回显
     * @param id
     * @return
     */
    District findById(Integer id);

    /**
     * 更新区域数据
     * @param district
     * @return
     */
    void updateDistrict(District district);

    /**
     * 根据id删除区域信息
     * @param id
     */
    void delById(Integer id);

    /**
     * 批量删除区域信息
     * @param integers
     * @return
     */
    int deleteMoreDistrict(Integer[] integers);

    /**
     * 查询所有区域信息
     * @return
     */
    List<District> findAll();

}
