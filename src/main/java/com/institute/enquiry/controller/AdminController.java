package com.institute.enquiry.controller;

import com.institute.enquiry.repository.EnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EnquiryRepository enquiryRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    // ✅ VIEW ALL ENQUIRIES
    @GetMapping("/enquiries")
    public String viewEnquiries(Model model) {
        model.addAttribute("enquiries", enquiryRepository.findAll());
        return "admin/enquiries";
    }
}
