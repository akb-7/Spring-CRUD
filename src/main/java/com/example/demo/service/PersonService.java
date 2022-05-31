package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personaDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personaDao) {
        this.personaDao = personaDao;
    }

    public int insertPerson(Person person) {
        return personaDao.insertPerson(person);

    }

    public List<Person> getAllPeople() {
        return personaDao.selectAllPeople();
    }

    public Optional<Person> getPersonbyId(UUID id) {
        return personaDao.selectPersonbyId(id);
    }

    public int deletePerson(UUID id) {
        return personaDao.deletePersonbyId(id);
    }
    
    public int updatePerson(UUID id,Person person) {
        return personaDao.updatePersonbyId(id,person);
    }

}
