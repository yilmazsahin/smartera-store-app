package com.smartera.customerservice.controller;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author yilmazsahin
 * @since 2/16/2024
 */
@Controller
public class WebController {
    @GetMapping("/")
    public String login() {
        return "login.html";
    }
}
