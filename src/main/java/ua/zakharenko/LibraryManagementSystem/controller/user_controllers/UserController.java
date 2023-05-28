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
import ua.zakharenko.LibraryManagementSystem.service.PersonService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public UserController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String homePage(Model model){
        return "userViews/homepage";
    }

    @GetMapping("/chooseBook")
    public String chooseBook(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("person", personDetails.getUser());
        return "/userViews/chooseBook";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("person", personDetails.getUser());
        model.addAttribute("books", personService.getBooks(personDetails.getUser().getId()));
        return "/userViews/profile";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") Long bookId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        bookService.assign(personDetails.getUser().getId(), bookId);
        return "redirect:/user";
    }

}