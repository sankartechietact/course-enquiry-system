package com.institute.enquiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.institute.enquiry.entity.Enquiry;
import com.institute.enquiry.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enquiry")
public class EnquiryController {

    private final Enquiry enquiryService;
    private final EmailService emailService;

    // OPEN ENQUIRY FORM (from course list)
    @GetMapping("/course/{courseName}")
    public String enquiryForm(@PathVariable String courseName, Model model) {
        Enquiry enquiry = new Enquiry();
        enquiry.setCourseName(courseName);
        model.addAttribute("enquiry", enquiry);
        return "public/enquiry";
    }

    // SUBMIT ENQUIRY
    @PostMapping("/submit")
    public String submitEnquiry(@Valid @ModelAttribute("enquiry") Enquiry enquiry,
                                BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            return "public/enquiry";
        }

        // Save to DB
        EmailService.save(enquiry);

        // Mail to Admin
        String body =
                "New Course Enquiry\n\n" +
                "Name: " + enquiry.getName() + "\n" +
                "Email: " + enquiry.getEmail() + "\n" +
                "Contact: " + enquiry.getContact() + "\n" +
                "Qualification: " + enquiry.getQualification() + "\n" +
                "Course: " + enquiry.getCourseName() + "\n" +
                "Message: " + enquiry.getMessage();

        emailService.sendEnquiryMail(
                "yourmail@gmail.com",
                "New Course Enquiry",
                body
        );

        model.addAttribute("success", "Enquiry submitted successfully!");
        model.addAttribute("enquiry", new Enquiry());
        return "public/enquiry";
    }
}
