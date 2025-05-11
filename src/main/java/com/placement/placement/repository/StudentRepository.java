package com.placement.placement.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.placement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByQrCode(String qrCode);


    

}