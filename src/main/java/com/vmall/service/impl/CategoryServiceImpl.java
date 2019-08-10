package com.vmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vmall.common.ServerResponse;
import com.vmall.dao.CategoryMapper;
import com.vmall.pojo.Category;
import com.vmall.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);   //该分类可用

        int rowCount = categoryMapper.insertSelective(category);
        if (rowCount >  0) {
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }

        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    public ServerResponse updateCategoryName(Integer categoryId,String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setId(categoryId);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount >  0) {
            return ServerResponse.createBySuccessMessage("更新品类成功");
        }

        return ServerResponse.createByErrorMessage("更新品类失败");
    }

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        if (categoryId == null ) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        List<Category> categoryList = categoryMapper.getCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("当前分类id的子节点为空");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse<List<Integer>> getChildrenDeepCategory(Integer categoryId) {
        if (categoryId == null ) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Set<Category> categorySet = Sets.newHashSet();
        deepCategory(categorySet, categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categorySet != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    public Set<Category> deepCategory(Set<Category> categorySet,Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点，递归算法一定要有一个退出递归的条件
        List<Category> categoryList = categoryMapper.getCategoryChildrenByParentId(categoryId);
        for(Category categoryTtem : categoryList) {
            deepCategory(categorySet,categoryTtem.getId());
        }
        return categorySet;
    }
}
