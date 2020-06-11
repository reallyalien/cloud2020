package com.ot.springcloud.service;

import com.ot.springcloud.entities.Payment;

public interface PaymentService {

    int insert(Payment payment);

    Payment getById(Long id);
}
