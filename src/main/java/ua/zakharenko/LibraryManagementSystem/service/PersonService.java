package ua.zakharenko.LibraryManagementSystem.service;

import ua.zakharenko.LibraryManagementSystem.entity.Book;
import ua.zakharenko.LibraryManagementSystem.entity.Person;

import java.util.List;

public interface PersonService {

    List<Book> getBooks(Long id);
}
