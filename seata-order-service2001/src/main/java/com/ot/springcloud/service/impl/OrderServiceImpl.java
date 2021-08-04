package com.ot.springcloud.service.impl;

import com.ot.springcloud.dao.OrderDao;
import com.ot.springcloud.entities.Order;
import com.ot.springcloud.service.AccountService;
import com.ot.springcloud.service.OrderService;
import com.ot.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class, name = "fsp-create-order")
    public void create(Order order) {

        log.info("----->开始新建订单");
        //1 新建订单
        orderDao.create(order);

        //2 扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //3 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        int a = 1 / 0;

        //4 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了，O(∩_∩)O哈哈~");

    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class, name = "fsp-create-order1")
    public void create1(Order order) {
        orderDao.create(order);
        log.info("开始扣减余额");
        accountService.decrease(order.getUserId(), order.getMoney());
    }


    //在mysql的默认隔离级别下，同时开始事务操作同一条数据，在第一个事务修改数据之后，第二个事务确实看不见修改之后的，但是在第二个
    //事务同样修改同一条数据时，此时mysql会添加行锁，修改会被阻塞，待第一个事务提交之后，第二个事务的修改操作才得以继续，此时，第
    //二个事务虽然看不见事务1修改之后提交的数据，但是待事务二提交之后，最终的数据就是事务1和事务2一起修改的结果,也就是即使代码不
    //加锁，mysql也会阻塞其他线程
}
