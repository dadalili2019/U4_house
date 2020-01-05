package cn.itcast.service.Impl;

import cn.itcast.dao.TypeMapper;
import cn.itcast.model.Type;
import cn.itcast.model.TypeExample;
import cn.itcast.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName TypeServiceImpl
 * @Date 2019/12/27 8:44
 * @Version 1.0
 */
@Service("typeService")
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有类型
     *
     * @return
     */
    @Override
    public List<Type> findAll() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
