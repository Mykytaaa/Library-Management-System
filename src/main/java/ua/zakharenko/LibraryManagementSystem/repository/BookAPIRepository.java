package ua.zakharenko.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.zakharenko.LibraryManagementSystem.entity.BookAPI;

@Repository
public interface BookAPIRepository extends JpaRepository<BookAPI, Long> {
}
