package ua.varus.testtask.service;

import ua.varus.testtask.model.Person;

import java.util.List;


public interface PersonService {

    void addPerson(Person person);

    void removePerson(int id);

     void updatePerson(Person person);

     Person getBookById(int id);

    List<Person> getAllPerson();

}
