package ua.zakharenko.LibraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.zakharenko.LibraryManagementSystem.security.PersonDetails;
import ua.zakharenko.LibraryManagementSystem.service.BookService;

@Controller
public class IndexController {

	private final BookService bookService;

	@Autowired
	public IndexController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("books", bookService.findAllBooksAPI());
		return "index";
	}
}
