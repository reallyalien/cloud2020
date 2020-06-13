package com.ot.springcloud.lb.impl;

import com.ot.springcloud.lb.LoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalance {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * CAS操作
     * @return
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current, next));//如果CAS操作成功，则退出while循环
        System.out.println("next:" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
