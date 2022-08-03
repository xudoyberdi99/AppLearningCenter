package com.company.payload;

import com.company.entity.enums.PayType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@Data
public class PaymentDto {

    @NotNull(message = "Please provide a sum")
    private double sum;

    private String description;

    private Integer studentId;

    @Enumerated(value = EnumType.STRING)
    private PayType payType;

}
