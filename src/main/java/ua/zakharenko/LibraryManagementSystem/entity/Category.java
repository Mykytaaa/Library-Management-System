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
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50, nullable = false, unique = true)
	@NotEmpty(message = "The category name should not be empty")
	@Length(min = 1, max = 50, message = "The length should be between 1 and 50")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE },
			mappedBy = "categories")
	private Set<Book> books = new HashSet<Book>();

	public Category(String name) {
		this.name = name;
	}

}
