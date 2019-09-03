package com.pinyougou.mapper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BaseMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    /**
     * 注入BaseMapper
     */
    @Autowired
    private BaseMapper<T> baseMapper;

    /**
     *1、 根据主键id查询
     */
    @Override
    public T findOne(Serializable id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 2、查询全部
     */
    @Override
    public List findAll() {
        return baseMapper.selectAll();
    }

    /**
     * 3、根据条件查询
     */
    @Override
    public List<T> findByWhere(T t) {
        return baseMapper.select(t);
    }

    /**
     * 4、开启分页查询
     */
    @Override
    public PageInfo findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<T> list = baseMapper.selectAll();
        return new PageInfo<T>(list);
    }

    /**
     * 5、条件分页查询
     */
    @Override
    public PageInfo findPage(Integer pageNum, Integer pageSize, T t) {
        PageHelper.startPage(pageNum,pageSize);

        List<T> list = baseMapper.select(t);
        return new PageInfo<T>(list);
    }

    /**
     * 6、添加品牌
     */
    @Override
    public void add(T t) {
        baseMapper.insertSelective(t);
    }




    /**
     * 7、根据条件id修改
     */
    @Override
    public void update(T id) {
        baseMapper.updateByPrimaryKeySelective(id);
    }

    /**
     * 8、批量删除
     *  StringUtils.join(ids,",") 数组间用逗号隔开
     */
    @Override
    public void deleteByIds(Serializable[] ids) {
        if(ids!=null && ids.length>0){
            baseMapper.deleteByIds(StringUtils.join(ids,","));
        }

    }

    public abstract List<TbBrand> queryAll();
}
