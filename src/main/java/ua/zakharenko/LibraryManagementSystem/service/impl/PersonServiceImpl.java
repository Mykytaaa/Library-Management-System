package ua.zakharenko.LibraryManagementSystem.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.zakharenko.LibraryManagementSystem.entity.Book;
import ua.zakharenko.LibraryManagementSystem.entity.Person;
import ua.zakharenko.LibraryManagementSystem.repository.PersonRepository;
import ua.zakharenko.LibraryManagementSystem.service.PersonService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findOne(Long id){
        return personRepository.findById(id).orElse(null);
    }
    @Override
    public List<Book> getBooks(Long id){
        Person person = findOne(id);
        Hibernate.initialize(person.getBookList());
        return person.getBookList();
    }
}
