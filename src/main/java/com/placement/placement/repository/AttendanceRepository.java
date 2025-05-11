package com.placement.placement.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.placement.model.Attendance;
import com.placement.placement.model.Tutor;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByTutor(Tutor tutor);
}
