package com.smartera.customerservice.controller;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.CustomerDTO;
import com.smartera.customerservice.entity.LoginRequest;
import com.smartera.customerservice.mapper.CustomerMapper;
import com.smartera.customerservice.service.AuthService;
//import jdk.incubator.vector.VectorOperators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yilmazsahin
 * @since 2/15/2024
 */
@Controller
@RequestMapping("/api")

public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerDTO> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        logger.info("Incoming request : {}", loginRequest);
        Customer customer = authService.authenticateUser(email, password);
        if (customer != null) {
            logger.info("Incoming request is correct {}", email + " " + password);
            return ResponseEntity.ok(CustomerMapper.toDTO(customer));
        } else {
            String errorMessage = "Authentication failed";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
    }
}
