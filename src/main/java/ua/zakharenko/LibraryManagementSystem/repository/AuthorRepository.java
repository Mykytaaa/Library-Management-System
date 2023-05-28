package ua.zakharenko.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.zakharenko.LibraryManagementSystem.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
