package cn.itcast.service.Impl;

import cn.itcast.dao.DistrictMapper;
import cn.itcast.dao.StreetMapper;
import cn.itcast.model.District;
import cn.itcast.model.DistrictExample;
import cn.itcast.service.DistrictService;
import cn.itcast.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author caoqian
 * @Date 2019/12/20 19:35
 * @Version 1.0
 */
//区域信息Service
@Service("districtService")
@Transactional
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;


    /**
     * 分页展示区域信息
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<District> findAllByPage(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getRows());
        DistrictExample districtExample = new DistrictExample();
        List<District> districts = districtMapper.selectByExample(districtExample);
        return new PageInfo<>(districts);
    }

    /**
     * 新增区域信息
     *
     * @param district
     * @return
     */

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    /**
     * 根据id查询区域信息进行修改回显
     *
     * @param id
     * @return
     */
    @Override
    public District findById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改区域信息
     *
     * @param district
     */
    @Override
    public void updateDistrict(District district) {
        districtMapper.updateByPrimaryKeySelective(district);
    }

    /**
     * 根据id删除区域信息
     *
     * @param id
     */
    @Override
    public void delById(Integer id) {
        //删除街道信息
        streetMapper.delByDistrictId(id);

        //删除区域信息
        districtMapper.deleteById(id);
    }

    /**
     * 批量删除街道信息
     * @param integers
     * @return
     */
    @Override
    public int deleteMoreDistrict(Integer[] integers) {
        //删除街道
        boolean b = streetMapper.deleteMoreDistrict(integers);
        int i=1/0;
        //删除区域
        boolean b1 = districtMapper.deleteMoreDistrict(integers);
        if (b == true && b1 == true) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 查询所有街道信息
     * @return
     */
    @Override
    public List<District> findAll() {
        return districtMapper.findAll();
    }
}
