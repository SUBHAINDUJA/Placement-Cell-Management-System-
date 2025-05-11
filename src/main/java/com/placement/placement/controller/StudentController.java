package com.placement.placement.controller;



import java.io.IOException;
import java.util.Base64;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;
import com.placement.placement.model.Student;
import com.placement.placement.repository.StudentRepository;
import com.placement.placement.repository.TutorRepository;
import com.placement.placement.service.QRService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final QRService qrService;
    
    public StudentController(StudentRepository studentRepository, 
                           TutorRepository tutorRepository,
                           QRService qrService) {
        this.studentRepository = studentRepository;
        this.tutorRepository = tutorRepository;
        this.qrService = qrService;
    }
    
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "student";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("tutors", tutorRepository.findAll());
        return "add-student";
    }
    
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) throws IOException, WriterException {
        student = studentRepository.save(student);
        byte[] qrCode = qrService.generateQRCodeImage(student);
        student.setQrCode("data:image/png;base64," + Base64.getEncoder().encodeToString(qrCode));
        studentRepository.save(student);
        return "redirect:/students";
    }
    
    @GetMapping(value = "/qr/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQRCode(@PathVariable Long id) throws Exception {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new Exception("Student not found"));
        return qrService.generateQRCodeImage(student);
    }
}