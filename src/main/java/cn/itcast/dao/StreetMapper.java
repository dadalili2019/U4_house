package cn.itcast.dao;

import cn.itcast.model.Street;
import cn.itcast.model.StreetExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    /**
     * 删除街道信息
     * @param id
     */
    @Delete("DELETE from street where DISTRICT_ID=#{id}")
    void delByDistrictId(@Param(value = "id") Integer id);

    /**
     * 批量删除街道信息
     * @param integers
     */
    boolean deleteMoreDistrict(Integer[] integers);

    /**
     * 根据id查询街道信息
     * @param did
     * @return
     */
    @Select("SELECT * FROM street WHERE DISTRICT_ID=#{did}")
    List<Street> findById(@Param(value = "did") Integer did);
}