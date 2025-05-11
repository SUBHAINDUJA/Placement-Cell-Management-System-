package com.placement.placement.service;




import com.placement.placement.repository.Resume;
import com.placement.placement.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    private static final String uploadDir = "C:/placement-uploads/";  // Directory for uploaded resumes
    private static final String RESUME_DIRECTORY = "C:/placement-resume-templates/";  // Directory for resume templates

    // Upload resume method
    public String uploadResume(MultipartFile file, String domain) throws IOException {
        Files.createDirectories(Paths.get(uploadDir));  // Ensure the directory exists

        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);
        Files.write(path, file.getBytes());

        // Simulate analysis
        String analysis = "ATS Score: 85/100. Keywords matched for " + domain;
        String recommendations = "Add a summary section. Highlight domain-specific skills.";

        // Create a new resume record and save to the database
        Resume resume = new Resume();
        resume.setFilePath(path.toString());
        resume.setDomain(domain);
        resume.setAnalysisSummary(analysis);
        resume.setRecommendations(recommendations);

        resumeRepository.save(resume);
        return "Resume uploaded and analyzed successfully";
    }

    // Get resume template file path based on domain
    public String getResumeTemplateFilePath(String domain) {
        switch (domain.toLowerCase()) {
            case "software":
                return RESUME_DIRECTORY + "software_resume_template.pdf";
            case "marketing":
                return RESUME_DIRECTORY + "marketing_resume_template.pdf";
            case "finance":
                return RESUME_DIRECTORY + "finance_resume_template.pdf";
            case "design":
                return RESUME_DIRECTORY + "design_resume_template.pdf";
            default:
                throw new IllegalArgumentException("Invalid domain: " + domain);
        }
    }

    public String downloadResume(String domain) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'downloadResume'");
    }
}
