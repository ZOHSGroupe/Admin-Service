package com.admin.web;

import com.admin.Exception.EmailAlreadyExistsException;
import com.admin.dto.*;
import com.admin.entity.Admin;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }
    @GetMapping("/{adminId}")
    public ResponseEntity<?> getAllAdmins(@PathVariable String adminId) {
        Admin admin= adminService.getAdminById(adminId);
        if (admin!=null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("Admin Not Found"), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse> addAdmin(@RequestBody AdminCreateDTO adminRequest) {
        try {
            Admin admin = adminService.addAdmin(adminRequest);
            return new ResponseEntity<>(new ApiResponse("Admin created successfully"), HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>(new ApiResponse("Email already exists"), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String adminId) {
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginDTO loginRequest) {
        // Assume LoginRequest is a DTO that contains username and password fields
        String loggedIn = adminService.login(loginRequest);

        if (loggedIn!=null) {
            return new ResponseEntity<>(new JwtResponse(loggedIn), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable String adminId, @RequestBody AdminUpdateDTO adminUpdateRequest) {
        // Assume AdminUpdateRequest is a DTO that contains newUsername, newEmail, and newPassword fields
        Admin updatedAdmin = adminService.updateAdmin(adminId, adminUpdateRequest);

        if (updatedAdmin != null) {
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

