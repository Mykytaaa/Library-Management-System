package ua.zakharenko.LibraryManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/403")
public class NotFoundController {

    @GetMapping
    public String e(){
        return "userViews/403";
    }
}
