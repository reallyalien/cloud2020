package com.ot.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ot.springcloud.entities.CommonResult;
import com.sun.deploy.security.BlockedException;
import org.springframework.stereotype.Component;

/**
 * 自定义限流处理逻辑
 */
@Component
public class CustomerBlockHandle {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handlerException----1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handlerException----2");
    }

}
