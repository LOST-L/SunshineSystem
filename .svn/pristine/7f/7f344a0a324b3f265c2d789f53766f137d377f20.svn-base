package com.heli.oa.plan.service.impl;

import com.heli.oa.plan.dao.CategoryMapper;
import com.heli.oa.plan.entity.Category;
import com.heli.oa.plan.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper cm;

    @Override
    public Integer insertSelective(Category c) throws Exception {
        return cm.insertSelective(c);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) throws Exception {
        return cm.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Category c) throws Exception {
        return cm.updateByPrimaryKeySelective(c);
    }

    @Override
    public Integer delListCategory(List<Category> categoryList) throws Exception {
        return cm.delListCategory(categoryList);
    }

    @Override
    public Integer selectAllCategoryTotal(Category c) throws Exception {
        return cm.selectAllCategoryTotal(c);
    }

    @Override
    public List<Category> selectAllCategory(Category c) throws Exception {
        return cm.selectAllCategory(c);
    }

    @Override
    public List<Category> selectCategoryPidByWid(Integer wid) throws Exception {
        return cm.selectCategoryPidByWid(wid);
    }

    @Override
    public Category selectByPrimaryKey(Integer id) throws Exception {
        return cm.selectByPrimaryKey(id);
    }
}
