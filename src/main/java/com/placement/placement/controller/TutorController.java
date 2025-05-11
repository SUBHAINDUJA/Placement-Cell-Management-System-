package com.placement.placement.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.placement.placement.model.Tutor;
import com.placement.placement.repository.TutorRepository;

@Controller
@RequestMapping("/tutors")
public class TutorController {
    private final TutorRepository tutorRepository;
    
    public TutorController(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
    
    @GetMapping
    public String listTutors(Model model) {
        model.addAttribute("tutors", tutorRepository.findAll());
        return "tutor";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "add-tutor";
    }
    
    @PostMapping("/add")
    public String addTutor(@ModelAttribute Tutor tutor) {
        tutorRepository.save(tutor);
        return "redirect:/tutors";
    }
}
