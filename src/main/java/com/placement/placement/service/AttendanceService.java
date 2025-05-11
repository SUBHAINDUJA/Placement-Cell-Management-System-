package com.placement.placement.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.placement.placement.model.Attendance;
import com.placement.placement.model.Student;
import com.placement.placement.model.Tutor;
import com.placement.placement.repository.AttendanceRepository;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
    
    public Attendance recordAttendance(Student student, Tutor tutor, String location) {
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setTutor(tutor);
        attendance.setCheckInTime(LocalDateTime.now());
        attendance.setLocation(location);
        attendance.setStatus("PRESENT");
        return attendanceRepository.save(attendance);
    }
    
    public List<Attendance> getAttendanceByTutor(Tutor tutor) {
        return attendanceRepository.findByTutor(tutor);
    }
}