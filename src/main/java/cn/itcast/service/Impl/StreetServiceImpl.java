package cn.itcast.service.Impl;

import cn.itcast.dao.StreetMapper;
import cn.itcast.model.Street;
import cn.itcast.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName StreetServiceImpl
 * @Date 2019/12/27 14:43
 * @Version 1.0
 */
@Service("streetService")
@Transactional
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    /**
     * 根据id查询街道信息
     * @param did
     * @return
     */
    @Override
    public List<Street> findById(Integer did) {
        return streetMapper.findById(did);
    }
}
