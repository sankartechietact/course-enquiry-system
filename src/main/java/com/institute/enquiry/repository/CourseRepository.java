package com.institute.enquiry.repository;

import com.institute.enquiry.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStatus(String status);
}
