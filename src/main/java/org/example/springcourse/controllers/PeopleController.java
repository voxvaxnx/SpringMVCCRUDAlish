package org.example.springcourse.controllers;

import org.example.springcourse.DAO.PersonDAO;
import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired //можно не писать так как у конструктора автоматом @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()  //тут ничего нет потому что есть @RequestMapping("/people")
    public String index(Model model){ //получим всех людей из DAO и передаем на отображение
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) { // Метод извлекает 1го человека из DAO по id и передаем на отображение
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) { //Метод возвращает форму создания нового пользователя тут так же можно использовать @ModelAttribute
        model.addAttribute("person", new Person()); // в конструкторе так же можно использовать @ModelAttribute тогда эта строчка не нужна
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") Person person){  // Метод за счет аннотации @ModelAttribute создает в модели объект Person и из тела Post запроса(формы) задает объекту Person поля через сеттеры
        personDAO.save(person);                                     // а тут мы сохраняем обьект в базу или список или ?
        return "redirect:/people"; //осуществляем редирект по ключевому слову redirect:
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person" , personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
