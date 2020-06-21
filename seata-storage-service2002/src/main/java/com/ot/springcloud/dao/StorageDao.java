package com.ot.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ot.springcloud.entities.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao extends BaseMapper<Storage> {

    //扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
