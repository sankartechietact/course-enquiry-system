package com.institute.enquiry.service;

import com.institute.enquiry.entity.Course;
import com.institute.enquiry.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getActiveCourses() {
        return courseRepository.findByStatus("ACTIVE");
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
