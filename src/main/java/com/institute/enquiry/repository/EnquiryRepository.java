package com.institute.enquiry.repository;

import com.institute.enquiry.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}
