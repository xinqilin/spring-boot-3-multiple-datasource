package com.bill.multipleDatasource.controller;

import com.bill.multipleDatasource.entity.Person;
import com.bill.multipleDatasource.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Bill.Lin 2024/6/19
 */
@RequestMapping("/person")
@RestController
public class Controller {

    private final PersonService personService;

    public Controller(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/list")
    public List<Person> listPerson() {
        return personService.listPerson();
    }
}
