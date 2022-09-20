package com.example.demo.Repositories;

import com.example.demo.Models.ViewPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewPersonRepository extends JpaRepository<ViewPerson, Long> {
    List<ViewPerson> findBySurname(String lastName);
    List<ViewPerson> findByName(String lastName);
    List<ViewPerson> findByCity(String lastName);
    List<ViewPerson> findByAddress(String lastName);
    ViewPerson findById(long id);
}
