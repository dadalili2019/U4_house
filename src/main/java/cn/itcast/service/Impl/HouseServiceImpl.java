package cn.itcast.service.Impl;

import cn.itcast.dao.HouseMapper;
import cn.itcast.model.House;
import cn.itcast.service.HouseService;
import cn.itcast.utils.PageUtil;
import cn.itcast.utils.SearchHouseCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName HouseServiceImpl
 * @Date 2019/12/27 15:18
 * @Version 1.0
 */
//房屋信息service
@Service("houseService")
@Transactional
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;


    /**
     * 添加房屋信息
     *
     * @param house
     */
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    /**
     * 分页查询房屋信息
     *
     * @param uid
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<House> getAllHouseByPage(Integer uid, PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getRows());
        List<House> houseList = houseMapper.findByPage(uid);
        return new PageInfo<>(houseList);
    }

    /**
     * 修改房屋信息回显
     *
     * @param id
     * @return
     */
    @Override
    public House findById(String id) {
        return houseMapper.findById(id);
    }

    /**
     * 修改房屋信息
     *
     * @param house
     */
    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    /**
     * 删除房屋信息
     *
     * @param id    房屋id
     * @param isDel 是否删除逻辑参数
     */
    @Override
    public void updateIsPass(String id, String isDel) {
        houseMapper.updateHouseIsDel(id, Integer.parseInt(isDel));
    }

    /**
     * 分页展示出租房屋信息(已通过审核+未自行删除)
     *
     * @param searchHouseCondition
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<House> getUiAllHouseByPage(SearchHouseCondition searchHouseCondition, PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houses=houseMapper.findByConditionOnPage(searchHouseCondition);
        return new PageInfo<>(houses);
    }
}
