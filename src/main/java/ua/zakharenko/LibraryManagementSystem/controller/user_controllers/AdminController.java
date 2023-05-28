package ua.zakharenko.LibraryManagementSystem.controller.user_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.zakharenko.LibraryManagementSystem.security.PersonDetails;
import ua.zakharenko.LibraryManagementSystem.service.BookService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;

    @Autowired
    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String adminPanel(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("person", personDetails.getUser());

        return "adminViews/adminPanel";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id")Long id){
        bookService.release(id);
        return "redirect:/admin";
    }
}
