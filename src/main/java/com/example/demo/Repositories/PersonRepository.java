package com.example.demo.Repositories;

import com.example.demo.Models.Person;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
        List<Person> findBySurname(String lastName);
        List<Person> findByName(String lastName);
        List<Person> findByCity(String lastName);
        List<Person> findByAddress(String lastName);
        Person findById(long id);
        @Procedure("SAVE_PERSON")
        void savePerson(String name, String surname, String city, String address);
}
