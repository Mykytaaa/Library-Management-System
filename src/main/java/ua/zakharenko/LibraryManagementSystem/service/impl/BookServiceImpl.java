package ua.zakharenko.LibraryManagementSystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.zakharenko.LibraryManagementSystem.entity.Book;
import ua.zakharenko.LibraryManagementSystem.entity.BookAPI;
import ua.zakharenko.LibraryManagementSystem.exception.NotFoundException;
import ua.zakharenko.LibraryManagementSystem.repository.BookAPIRepository;
import ua.zakharenko.LibraryManagementSystem.repository.BookRepository;
import ua.zakharenko.LibraryManagementSystem.repository.PersonRepository;
import ua.zakharenko.LibraryManagementSystem.service.BookService;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final PersonRepository personRepository;

	private final BookAPIRepository bookAPIRepository;

	public BookServiceImpl(BookRepository bookRepository, PersonRepository personRepository, BookAPIRepository bookAPIRepository) {
		this.bookRepository = bookRepository;
		this.personRepository = personRepository;
		this.bookAPIRepository = bookAPIRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Book> searchBooks(String keyword) {
		if (keyword != null) {
			return bookRepository.search(keyword);
		}
		return bookRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Book findBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
	}

	@Override
	public void createBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void createBookAPI(BookAPI bookAPI){
		bookAPIRepository.save(bookAPI);
	}

	public List<BookAPI> findAllBooksAPI(){
		return bookAPIRepository.findAll();
	}

	@Override
	public void updateBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		final Book book = bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

		bookRepository.deleteById(book.getId());
	}

	@Transactional
	public void assign(Long customerId, Long bookId){
		Book book = findBookById(bookId);
		book.setOwner(Objects.requireNonNull(personRepository.findById(customerId).orElse(null)));
	}

	@Transactional
	public void release(Long id){
		Book book = findBookById(id);
		book.setOwner(null);
	}
}
