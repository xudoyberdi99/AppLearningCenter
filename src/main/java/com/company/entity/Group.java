package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "groups")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course  course;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    private Date starDate;

    private Date endDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Student> students;
}
