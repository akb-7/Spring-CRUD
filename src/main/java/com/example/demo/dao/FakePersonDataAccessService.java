package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonbyId(UUID id) {
        return DB.stream()
                .filter(person -> person.getId()
                        .equals(id))
                        .findFirst();
    }

    @Override
    public int deletePersonbyId(UUID id) {
        Optional<Person> personMaybe = selectPersonbyId(id);
        if (personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 0;
    }

    @Override
    public int updatePersonbyId(UUID id, Person person) {
        return selectPersonbyId(id)
                .map(p -> {
                    int indexPersonToDelete = DB.indexOf(p);
                    if(indexPersonToDelete>=0) {
                        DB.set(indexPersonToDelete,new Person(id,person.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
