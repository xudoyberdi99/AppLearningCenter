package com.company.service.serviceImpl;

import com.company.entity.Payment;
import com.company.entity.Student;
import com.company.payload.ApiResponse;
import com.company.payload.PaymentDto;
import com.company.repository.PaymentRepository;
import com.company.repository.StudentRepository;
import com.company.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getByPaymentId(Integer id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.orElse(new Payment());
    }

    @Override
    public ApiResponse addPayment(PaymentDto paymentDto) {
        Optional<Student> student = studentRepository.findById(paymentDto.getStudentId());
        if (!student.isPresent()){
            return new ApiResponse("not found student",false);
        }
         Payment payment=new Payment();
         payment.setSum(paymentDto.getSum());
         payment.setDescription(paymentDto.getDescription());
         payment.setStudent(student.get());
         payment.setPayType(paymentDto.getPayType());

         paymentRepository.save(payment);
        return new ApiResponse("payment succes",true);
    }

    @Override
    public ApiResponse deletePayment(Integer id) {
        try{
            paymentRepository.deleteById(id);
            return new ApiResponse("delete payment",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
