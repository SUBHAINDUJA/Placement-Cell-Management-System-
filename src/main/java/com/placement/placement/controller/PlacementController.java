package com.placement.placement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



    @Controller
@RequestMapping("/placement")
public class PlacementController 
{
    @GetMapping("")
    public String redirectToHome() {
        return "redirect:/";  // Redirects to the root (home.html)
    }



    @GetMapping("/introduction")
    public String introduction() {
        return "introduction"; // resolves to introduction.html in templates folder
    }

    @GetMapping("/recruiters")
    public String recruiters() {
        return "recruiters";
    }
@GetMapping("/training/innovations")
public String innovations() {
    return "innovations"; // returns innovations.html
}

@GetMapping("/innovations/resume-analyzer")
    public String resumeAnalyzer() {
        return "resume-analyzer"; // resolves to resume-analyzer.html
    }

    
    
}

