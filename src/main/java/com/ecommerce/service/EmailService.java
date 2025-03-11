package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    // ===== CUSTOMER EMAILS =====

    //. Customer - Registered Email
    public void sendCustomerApprovedEmail(String toEmail, String customerName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome! Your Account is Registered");
        message.setText("Dear " + customerName + ",\n\n" +
                "Great news! Your account has been successfully Registered. You can now log in and start using our services.\n\n" +
                "Click here to login: https://E-Commerce.com/login\n\n" +
                "Best Regards,\nE-Commerce Team");

        mailSender.send(message);
    }
    // ===== VENDOR EMAILS =====

    // Vendor - Registered (Pending) Email
    public void sendVendorRegisteredEmail(String toEmail, String vendorName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Vendor Registration Pending");
        message.setText("Dear " + vendorName + ",\n\n" +
                "Thank you for registering as a vendor! Your account is currently under review. We will notify you once the verification is complete.\n\n" +
                "Best Regards,\nE-Commerce Team");

        mailSender.send(message);
    }

    // Vendor - Approved Email
    public void sendVendorApprovedEmail(String toEmail, String vendorName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Vendor Registration Approved");
        message.setText("Dear " + vendorName + ",\n\n" +
                "Congratulations! Your vendor account has been approved. You can now start listing your products/services on our platform.\n\n" +
                "Click here to login: https://E-Commerce.com/login\n\n"+
                "Best Regards,\nE-Commerce Team");

        mailSender.send(message);
    }

    // Vendor - Rejected Email
    public void sendVendorRejectedEmail(String toEmail, String vendorName, String reason) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Vendor Registration Rejected");
        message.setText("Dear " + vendorName + ",\n\n" +
                "We regret to inform you that your vendor registration has been rejected due to the following reason:\n\n" +
                "Reason: " + reason + "\n\n" +
                "Please review the issue and reapply.\n\n" +
                "Best Regards,\nE-Commerce Team");

        mailSender.send(message);
    }
}
