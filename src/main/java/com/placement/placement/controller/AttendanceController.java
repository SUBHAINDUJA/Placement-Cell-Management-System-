package com.placement.placement.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.placement.placement.model.Attendance;
import com.placement.placement.model.Student;
import com.placement.placement.model.Tutor;
import com.placement.placement.repository.StudentRepository;
import com.placement.placement.repository.TutorRepository;
import com.placement.placement.service.AttendanceService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    
    public AttendanceController(AttendanceService attendanceService,
                              StudentRepository studentRepository,
                              TutorRepository tutorRepository) {
        this.attendanceService = attendanceService;
        this.studentRepository = studentRepository;
        this.tutorRepository = tutorRepository;
    }
    
    @GetMapping("/scan")
    public String showScanner(Model model, @RequestParam Long tutorId) {
        model.addAttribute("tutorId", tutorId);
        return "attendance";
    }
    
    @PostMapping("/record")
    public String recordAttendance(@RequestParam String qrData,
                                @RequestParam Long tutorId,
                                @RequestParam String location,
                                Model model) {
        try {
            // 1. Parse and validate QR data
            if (qrData == null || !qrData.contains(":")) {
                throw new IllegalArgumentException("Invalid QR code format");
            }
            
            // 2. Extract student ID
            Long studentId;
            try {
                studentId = Long.parseLong(qrData.split(":")[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid student ID in QR code");
            }
            
            // 3. Verify student exists
            Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student with ID " + studentId + " not found"));
            
            // 4. Verify tutor exists
            Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor with ID " + tutorId + " not found"));
            
            // 5. Record attendance
            Attendance attendance = attendanceService.recordAttendance(student, tutor, location);
            
            // 6. Add all necessary attributes to model
            model.addAttribute("message", "Attendance recorded for " + student.getName());
            model.addAttribute("attendance", attendance);
            model.addAttribute("student", student);
            model.addAttribute("tutor", tutor);
            
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Error recording attendance: " + e.getMessage());
        }
        
        return "attendance-result";
    }
    
    @GetMapping("/view/{tutorId}")
    public String viewAttendance(@PathVariable Long tutorId, Model model) {
        Tutor tutor = tutorRepository.findById(tutorId)
            .orElseThrow(() -> new RuntimeException("Tutor not found"));
        
        // Add both tutor and attendances to the model
        model.addAttribute("tutor", tutor);  // This was missing
        model.addAttribute("attendances", attendanceService.getAttendanceByTutor(tutor));
        
        return "attendance-view";
    }
}