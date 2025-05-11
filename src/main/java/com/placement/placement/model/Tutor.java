package com.placement.placement.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tutors")  // Explicit table name mapping
@Data
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)  // Not null with max length
    private String name;
    
    @Column(nullable = false, unique = true, length = 100)  // Unique email constraint
    private String email;
    
    @Column(length = 20)  // Phone number length limit
    private String phone;
    
    @Column(nullable = false, length = 100)  // Department cannot be null
    private String department;
    
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;
}