package cn.itcast.dao;

import cn.itcast.model.House;
import cn.itcast.model.HouseExample;
import cn.itcast.utils.SearchHouseCondition;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    /**
     * 分页展示房屋信息
     * @return
     */
    List<House> findByPage(Integer uid);

    /**
     * 根据id查询房屋信息进行回显
     * @param did
     * @return
     */
    House findById(String did);

    /**
     * 根据id修改房屋是否删除的信息
     * @param id
     * @param parseInt
     */
    @Update("UPDATE house SET isDel=#{parseInt} WHERE id= #{id}")
    void updateHouseIsDel(@Param(value = "id") String id, @Param(value = "parseInt") Integer parseInt);

    /**
     * 查询所有房屋信息
     * @return
     */
    @Select("SELECT * FROM house")
    List<House> findAll();

    /**
     * 根据查询条件显示房屋出租信息（已通过审核+用户未自行删除）
     * @param searchHouseCondition
     * @return
     */
    List<House> findByConditionOnPage(SearchHouseCondition searchHouseCondition);

    /**
     * 审核房屋通过房屋信息
     * @param id
     * @return
     */
    @Update("UPDATE house SET ispass=1 WHERE id= #{id}")
    int getPassById(@Param(value = "id") String id);

    /**
     * 根据房屋id查询房屋详细信息
     * @param parseInt
     * @return
     */
    House findUserHouseById(String parseInt);
}