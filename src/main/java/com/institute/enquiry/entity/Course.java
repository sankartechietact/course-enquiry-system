package com.institute.enquiry.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    private String name;

    @NotBlank(message = "Course description is required")
    @Column(length = 2000)
    private String description;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotNull(message = "Fees is required")
    private Double fees;

    // FontAwesome icon class (eg: fa-solid fa-code)
    private String icon;

    // Brochure file name (PDF)
    private String brochure;

    // ACTIVE / INACTIVE
    private String status = "ACTIVE";
}
