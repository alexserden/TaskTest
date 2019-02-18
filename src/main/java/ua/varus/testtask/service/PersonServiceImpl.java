package ua.varus.testtask.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.varus.testtask.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service(value = "personService")
@Repository
public class PersonServiceImpl implements PersonService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void addPerson(Person person) {
        entityManager.persist(person);
    }

    @Override
    @Transactional
    public void removePerson(int id) {
        Person person = getBookById(id);
        Person mergePerson = entityManager.merge(person);
        entityManager.remove(mergePerson);

    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        entityManager.merge(person);
    }

    @Override
    @Transactional
    public Person getBookById(int id) {
        TypedQuery<Person> query = entityManager.createNamedQuery("Person.findById", Person.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<Person> getAllPerson() {
        List<Person> personList = entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
        return personList;
    }

}