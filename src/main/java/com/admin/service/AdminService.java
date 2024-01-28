package com.admin.service;

import com.admin.Exception.EmailAlreadyExistsException;
import com.admin.dto.AdminCreateDTO;
import com.admin.dto.AdminLoginDTO;
import com.admin.dto.AdminUpdateDTO;
import com.admin.entity.Admin;
import com.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    public Admin updateAdmin(String id,AdminUpdateDTO adminUpdateDTO) {
        Admin admin = adminRepository.findById(id).orElse(null);

        if (admin != null) {
            if (adminUpdateDTO.getUsername() != null) {
                admin.setUsername(adminUpdateDTO.getUsername());
            }

            if (adminUpdateDTO.getEmail() != null) {
                admin.setEmail(adminUpdateDTO.getEmail());
            }

            if (adminUpdateDTO.getPassword() != null) {
                admin.setPassword(passwordEncoder.encode(adminUpdateDTO.getPassword()));
            }

            return adminRepository.save(admin);
        }

        return null;
    }
    public void deleteAdmin(String adminId) {
        adminRepository.deleteById(adminId);
    }

    public Admin addAdmin(AdminCreateDTO adminCreateDTO) {
        // Check if an admin with the same email already exists
        if (adminRepository.existsByEmail(adminCreateDTO.getEmail())) {
            // You might want to throw an exception or handle the case where the email is not unique
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Admin admin = new Admin();
        admin.setUsername(adminCreateDTO.getUsername());
        admin.setEmail(adminCreateDTO.getEmail());
        admin.setPassword(passwordEncoder.encode(adminCreateDTO.getPassword()));

        return adminRepository.save(admin);
    }

    public String login(AdminLoginDTO adminLoginDTO) {

        Optional<Admin> optionalAdmin = adminRepository.findByEmail(adminLoginDTO.getEmail());

        if (optionalAdmin.isPresent() && passwordEncoder.matches(adminLoginDTO.getPassword(), optionalAdmin.get().getPassword())) {
            // Generate JWT token
            String token = Jwts.builder()
                    .setSubject(optionalAdmin.get().getUsername())
                    .claim("idAdmin", optionalAdmin.get().getId())
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();

            return token;
        }

        return null;
    }
    public Admin getAdminById(String id){
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if(optionalAdmin.isPresent()){
            return optionalAdmin.get();
        }
        return null;
    }
}
