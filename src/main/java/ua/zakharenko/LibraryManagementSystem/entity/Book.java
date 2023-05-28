package ua.zakharenko.LibraryManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isbn", length = 50, nullable = false, unique = true)
	@Pattern(regexp = "\\d{13}", message = "You should write the ISBN code " +
			"ISBN-13 patterns 9783161484100")
	private String isbn;

	@Column(name = "name", length = 100, nullable = false)
	@NotEmpty(message = "The name of the book should not be empty")
	@Length(min = 1, max = 100, message = "The length should be between 1 and 100")
	private String name;

	@Column(name = "serialname", length = 50, nullable = false)
	@NotEmpty(message = "The serial name of the book should not be empty")
	@Length(min = 1, max = 50, message = "The length should be between 1 and 50")
	private String serialName;

	@Column(name = "description", length = 250, nullable = false)
	@NotEmpty(message = "The description of the book should not be empty")
	@Length(min = 5, max = 250, message = "The length should be between 5 and 250")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private Set<Author> authors = new HashSet<Author>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	private Set<Category> categories = new HashSet<Category>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_publishers", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "publisher_id") })
	private Set<Publisher> publishers = new HashSet<Publisher>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Person owner;

	public Book(String isbn, String name, String serialName, String description) {
		this.isbn = isbn;
		this.name = name;
		this.serialName = serialName;
		this.description = description;
	}

	public void setOwner(Person person){
		if(person == null){
			this.getOwner().getBookList().remove(this);
		}

		owner = person;

		if(person != null){
			person.getBookList().add(this);
		}
	}

	public void addAuthors(Author author) {
		this.authors.add(author);
		author.getBooks().add(this);
	}

	public void removeAuthors(Author author) {
		this.authors.remove(author);
		author.getBooks().remove(this);
	}

	public void addCategories(Category category) {
		this.categories.add(category);
		category.getBooks().add(this);
	}

	public void removeCategories(Category category) {
		this.categories.remove(category);
		category.getBooks().remove(this);
	}

	public void addPublishers(Publisher publisher) {
		this.publishers.add(publisher);
		publisher.getBooks().add(this);
	}

	public void removePublishers(Publisher publisher) {
		this.publishers.remove(publisher);
		publisher.getBooks().remove(this);
	}
}
