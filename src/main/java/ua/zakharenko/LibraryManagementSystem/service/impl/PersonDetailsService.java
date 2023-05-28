package ua.zakharenko.LibraryManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.zakharenko.LibraryManagementSystem.entity.Person;
import ua.zakharenko.LibraryManagementSystem.exception.NotFoundException;
import ua.zakharenko.LibraryManagementSystem.repository.PersonRepository;
import ua.zakharenko.LibraryManagementSystem.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByEmail(email);

        if(user.isEmpty())
            throw new NotFoundException("User not found, please check email");

        System.out.println(user.get());

        return new PersonDetails(user.get());
    }
}
