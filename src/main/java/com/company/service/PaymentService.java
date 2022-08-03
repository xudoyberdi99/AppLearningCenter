package com.company.service;

import com.company.entity.Payment;
import com.company.payload.ApiResponse;
import com.company.payload.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayment();

    Payment getByPaymentId(Integer id);

    ApiResponse addPayment(PaymentDto paymentDto);

    ApiResponse deletePayment(Integer id);
}
