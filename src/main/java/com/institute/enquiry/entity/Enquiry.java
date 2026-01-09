package com.institute.enquiry.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enquiry")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Enter valid 10 digit contact number")
    private String contact;

    @NotBlank(message = "Qualification is required")
    private String qualification;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotBlank(message = "Message is required")
    @Column(length = 1000)
    private String message;

    private LocalDateTime enquiryDate = LocalDateTime.now();
}
