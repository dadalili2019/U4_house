package cn.itcast.service.Impl;

import cn.itcast.dao.HouseMapper;
import cn.itcast.model.House;
import cn.itcast.model.HouseExample;
import cn.itcast.service.AdminHouseService;
import cn.itcast.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName AdminHouseServiceImpl
 * @Date 2019/12/31 16:30
 * @Version 1.0
 */
@Service("adminHouseService")
@Transactional
public class AdminHouseServiceImpl implements AdminHouseService {


    @Autowired
    private HouseMapper houseMapper;
    
    /**
     * 分页展示区域信息
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<House> findAllByPage(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getRows());
        HouseExample houseExample = new HouseExample();
        List<House> districts = houseMapper.selectByExample(houseExample);
        return new PageInfo<>(districts);
    }

    /**
     * 新增区域信息
     *
     * @param house
     * @return
     */

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    /**
     * 根据id查询区域信息进行修改回显
     *
     * @param id
     * @return
     */
    @Override
    public House findById(Integer id) {
        return houseMapper.selectByPrimaryKey(String.valueOf(id));
    }

    /**
     * 修改区域信息
     *
     * @param house
     */
    @Override
    public void updateHouse(House house) {
        houseMapper.updateByPrimaryKeySelective(house);
    }

//    /**
//     * 根据id删除区域信息
//     *
//     * @param id
//     */
//    @Override
//    public void delById(Integer id) {
//        //删除街道信息
//        streetMapper.delByDistrictId(id);
//
//        //删除区域信息
//        houseMapper.deleteById(id);
//    }
//
//    /**
//     * 批量删除街道信息
//     * @param integers
//     * @return
//     */
//    @Override
//    public int deleteMoreHouse(Integer[] integers) {
//        //删除街道
//        boolean b = streetMapper.deleteMoreDistrict(integers);
//        int i=1/0;
//        //删除区域
//        boolean b1 = houseMapper.deleteMoreDistrict(integers);
//        if (b == true && b1 == true) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }

    /**
     * 查询所有街道信息
     * @return
     */
    @Override
    public List<House> findAll() {
        return houseMapper.findAll();
    }

}
