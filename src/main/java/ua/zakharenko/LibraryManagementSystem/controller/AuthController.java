package ua.zakharenko.LibraryManagementSystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.zakharenko.LibraryManagementSystem.entity.Person;
import ua.zakharenko.LibraryManagementSystem.service.impl.RegistrationServiceImpl;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationServiceImpl registrationService;

    @Autowired
    public AuthController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationForm(@ModelAttribute("person")Person person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String signUp(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/auth/registration";
        }
        registrationService.signUp(person);

        return "redirect:/auth/login";
    }
}
