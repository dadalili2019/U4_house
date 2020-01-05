package cn.itcast.dao;

import cn.itcast.model.District;
import cn.itcast.model.DistrictExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    /**
     * 根据id删除区域信息
     * @param id
     */
    @Delete("delete from district where id=#{id}")
    void deleteById(@Param(value = "id") Integer id);

    /**
     * 批量删除区域信息
     * @param integers
     * @return
     */
    boolean deleteMoreDistrict(Integer[] integers);

    /**
     * 查询所有街道信息
     * @return
     */
    @Select("SELECT * FROM district")
    List<District> findAll();
}