package com.ot.springcloud.controller;

import com.ot.springcloud.entities.CommonResult;
import com.ot.springcloud.entities.Order;
import com.ot.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    public CommonResult create(@RequestBody Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

    @PostMapping("/order/create1")
    public CommonResult create1(@RequestBody Order order) {
        orderService.create1(order);
        return new CommonResult(200, "订单创建成功");
    }
}
