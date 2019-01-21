package com.heli.oa.plan.dao;

import com.heli.oa.plan.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryMapper {

    Integer insertSelective(Category c);

    Integer insert(Category c);

    int deleteByPrimaryKey(Integer id);

    Integer delListCategory(List<Category> categoryList);

    Integer updateByPrimaryKeySelective(Category c);

    Integer updateByPrimaryKey(Category c);

    Integer selectAllCategoryTotal(Category c) throws Exception;

    List<Category> selectAllCategory(Category c) throws Exception;

    List<Category> selectCategoryPidByWid(Integer wid) throws Exception;

    Category selectByPrimaryKey(Integer id);
}
