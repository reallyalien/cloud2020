package com.ot.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalance {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
