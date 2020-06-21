package com.ot.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ot.springcloud.entities.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountDao extends BaseMapper<Account> {


    /**
     * 扣减账户余额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
