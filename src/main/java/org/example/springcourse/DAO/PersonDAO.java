package org.example.springcourse.DAO;

import org.example.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;


    { //статический блок добавления Person в список
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Jerry"));
        people.add(new Person(++PEOPLE_COUNT, "Spike"));
        people.add(new Person(++PEOPLE_COUNT, "BabkaNiger"));

    }

    public List<Person> index(){ //отдаем список
        //...код для получаем из базы данных
        return people;
    }
    public Person show (int id) {  //отдаем список по id используем стримы и лямбды
        //...код для получаем из базы данных по id
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

    }
    public void save(Person person) { //метод для сохранения объекта Person
        //...код для сохраняем в базу данных
       person.setId(++PEOPLE_COUNT);
       people.add(person);

    }

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
