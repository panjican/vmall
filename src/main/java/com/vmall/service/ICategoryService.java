package com.vmall.service;

import com.vmall.common.ServerResponse;
import com.vmall.pojo.Category;

import java.util.List;
import java.util.Set;

public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId,String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> getChildrenDeepCategory(Integer categoryId);

    Set<Category> deepCategory(Set<Category> categorySet, Integer categoryId);
}
