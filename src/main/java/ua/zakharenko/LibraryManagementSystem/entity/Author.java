package ua.zakharenko.LibraryManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100, nullable = false,unique = true)
	@NotEmpty(message = "The field should not be empty")
	@Length(min = 1, max = 100, message = "The length should be between 1 and 100 characters")
	private String name;

	@Column(name = "description", length = 250, nullable = false)
	@NotEmpty(message = "Please add a description")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "authors")
	private Set<Book> books = new HashSet<Book>();

	public Author(String name, String description) {
		this.name = name;
		this.description = description;
	}

}
