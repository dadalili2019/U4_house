package cn.itcast.service;

import cn.itcast.model.Type;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName TypeService
 * @Date 2019/12/27 8:43
 * @Version 1.0
 */
public interface TypeService {

    /**
     * 查询所有类型
     * @return
     */
    List<Type> findAll();
}
