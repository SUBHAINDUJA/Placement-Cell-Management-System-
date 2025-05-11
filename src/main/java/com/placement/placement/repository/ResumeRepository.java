package com.placement.placement.repository;



import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    String getTemplateUrlByDomain(String domain);
}
