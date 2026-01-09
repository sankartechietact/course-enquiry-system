package com.institute.enquiry.controller;

import com.institute.enquiry.entity.Course;
import com.institute.enquiry.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // ADMIN – List Courses
    @GetMapping("/admin/courses")
    public String adminCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses";
    }

    // ADMIN – Add Course Page
    @GetMapping("/admin/courses/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "admin/add-course";
    }

    // ADMIN – Save Course
    @PostMapping("/admin/courses/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-course";
        }
        courseService.saveCourse(course);
        return "redirect:/admin/courses";
    }

    // ADMIN – Edit Course
    @GetMapping("/admin/courses/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getById(id));
        return "admin/edit-course";
    }

    // ADMIN – Delete Course
    @GetMapping("/admin/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/admin/courses";
    }

    // PUBLIC – Course List
    @GetMapping({"/", "/courses"})
    public String publicCourses(Model model) {
        model.addAttribute("courses", courseService.getActiveCourses());
        return "public/courses";
    }
}
