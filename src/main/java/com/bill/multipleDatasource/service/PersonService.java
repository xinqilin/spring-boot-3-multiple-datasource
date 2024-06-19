package com.bill.multipleDatasource.service;

import com.bill.multipleDatasource.dao.PersonDao;
import com.bill.multipleDatasource.entity.Person;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bill.Lin 2024/6/19
 */
@Service
@Log4j2
public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @PostConstruct
    public void init() {
        log.info("insert mock data");
        Person bill = Person.builder().name("Bill").build();
        Person sherry = Person.builder().name("Sherry").build();
        personDao.save(bill);
        personDao.save(sherry);
    }

    public List<Person> listPerson() {
        return personDao.findAll();
    }
}
