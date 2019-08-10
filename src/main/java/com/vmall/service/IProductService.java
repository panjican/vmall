package com.vmall.service;

import com.github.pagehelper.PageInfo;
import com.vmall.common.ServerResponse;
import com.vmall.pojo.Product;
import com.vmall.vo.ProductDetailVo;

public interface IProductService {

    ServerResponse saveProduct(Product product);

    ServerResponse setProductStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(Integer pageNum, Integer pageSize);

    ServerResponse<PageInfo> searchProduct(String productName,Integer productId,Integer pageNum, Integer pageSize);

    ServerResponse<ProductDetailVo> getSaleProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, Integer pageNum, Integer pageSize, String orderBy);
}
