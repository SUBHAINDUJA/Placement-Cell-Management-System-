package com.placement.placement.controller;




import com.placement.placement.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/uploadResume")
    public @ResponseBody String uploadResume(@RequestPart("file") MultipartFile file, @RequestParam("domain") String domain) {
        try {
            return resumeService.uploadResume(file, domain);
        } catch (Exception e) {
            return "Error uploading resume: " + e.getMessage();
        }
    }

    
}
