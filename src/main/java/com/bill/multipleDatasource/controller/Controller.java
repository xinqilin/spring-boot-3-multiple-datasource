package com.bill.multipleDatasource.controller;

import com.bill.multipleDatasource.entity.Person;
import com.bill.multipleDatasource.service.PersonReadService;
import com.bill.multipleDatasource.service.PersonWriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bill.Lin 2024/6/19
 */
@RequestMapping("/person")
@RestController
public class Controller {

    private final PersonWriteService personWriteService;
    private final PersonReadService personReadService;

    public Controller(PersonWriteService personWriteService, PersonReadService personReadService) {
        this.personWriteService = personWriteService;
        this.personReadService = personReadService;
    }

    @GetMapping("/list")
    public List<Person> listPerson() {
        return personReadService.listPerson();
    }

    @PostMapping("")
    public List<Person> listPerson(@RequestParam("name") String name) {
        return personWriteService.addPerson(name);
    }
}
