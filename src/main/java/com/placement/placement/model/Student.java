package com.placement.placement.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "students")  // Explicitly specifies the table name in the database
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)  // Adds not null constraint
    private String name;
    
    @Column(nullable = false, unique = true)  // Adds not null and unique constraints
    private String email;
    
    private String phone;
    
    @Column(nullable = false)
    private String course;
    
    @Column(nullable = false)
    private String university;
    
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
    
    @Column(name = "qr_code")  // Maps to qr_code column in database
    private String qrCode;
}