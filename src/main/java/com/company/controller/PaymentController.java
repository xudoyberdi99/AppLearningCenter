package com.company.controller;

import com.company.entity.Payment;
import com.company.payload.ApiResponse;
import com.company.payload.PaymentDto;
import com.company.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PreAuthorize(value ="hasAuthority('VIEW_PAYMENTS')")
    @GetMapping
    public HttpEntity<?> getAllPayment(){
        List<Payment> payments=paymentService.getAllPayment();
        return ResponseEntity.ok(payments);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_PAYMENTS')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByPaymentId(@PathVariable Integer id){
        Payment payment=paymentService.getByPaymentId(id);
        return ResponseEntity.ok(payment);
    }
    @PreAuthorize(value ="hasAuthority('ADD_PAYMENT')")
    @PostMapping
    public HttpEntity<?> addPayment(@Valid @RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse=paymentService.addPayment(paymentDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }

    @PreAuthorize(value ="hasAuthority('DELETE_PAYMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePayment(@PathVariable Integer id){
        ApiResponse apiResponse=paymentService.deletePayment(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
