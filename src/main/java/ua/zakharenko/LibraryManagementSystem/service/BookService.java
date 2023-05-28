package ua.zakharenko.LibraryManagementSystem.service;


import ua.zakharenko.LibraryManagementSystem.entity.Book;
import ua.zakharenko.LibraryManagementSystem.entity.BookAPI;

import java.util.List;

public interface BookService {

	public List<Book> findAllBooks();
	
	public List<Book> searchBooks(String keyword);

	public Book findBookById(Long id);

	public void createBook(Book book);

	public void updateBook(Book book);

	public void deleteBook(Long id);

	public void assign(Long customerId, Long bookId);

	public void release(Long id);

	public void createBookAPI(BookAPI bookAPI);

	public List<BookAPI> findAllBooksAPI();

}
