package ua.zakharenko.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.zakharenko.LibraryManagementSystem.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
