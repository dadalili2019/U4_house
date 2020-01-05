package cn.itcast.service;

import cn.itcast.model.Street;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName StreetService
 * @Date 2019/12/27 14:43
 * @Version 1.0
 */
public interface StreetService {

    /**
     * 根据id查询街道信息
     * @param did
     * @return
     */
    List<Street> findById(Integer did);
}
