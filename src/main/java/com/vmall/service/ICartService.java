package com.vmall.service;

import com.vmall.common.ServerResponse;
import com.vmall.vo.CartVo;

public interface ICartService {
    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> delete(Integer userId, String productIds);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer checked,Integer productId);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
