package cn.itcast.dao;

import cn.itcast.model.UserHouseMsg;
import cn.itcast.model.Users;
import cn.itcast.model.UsersExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     * 验证用户名是否存在
     * @param username
     * @return
     */
    @Select("select * from users where name =#{username}")
    List<Users> checkUserName(@Param(value = "username") String username);

    /**
     * 用户登录
     * @param users
     * @return
     */
    @Select("SELECT * FROM users WHERE NAME=#{name}  AND PASSWORD=#{password}")
    Users findUser(Users users);

    /**
     * 根据用户id+房屋id查询房屋详细信息
     * @param userHouseMsg+hid
     * @return
     */
    Users getHouseMsgById(UserHouseMsg userHouseMsg);
}