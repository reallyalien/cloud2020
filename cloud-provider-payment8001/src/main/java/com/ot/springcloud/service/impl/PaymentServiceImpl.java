package com.ot.springcloud.service.impl;

import com.ot.springcloud.dao.PaymentDao;
import com.ot.springcloud.entities.Payment;
import com.ot.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentDao.selectById(id);
    }
}
