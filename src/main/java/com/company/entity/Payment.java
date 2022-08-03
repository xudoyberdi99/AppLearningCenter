package com.company.entity;

import com.company.entity.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private double sum;

    private String description;

    @ManyToOne(optional = false)
    private Student student;

    @Enumerated(value = EnumType.STRING)
    private PayType payType;

    @CreationTimestamp
    private Timestamp createdDate;
}
