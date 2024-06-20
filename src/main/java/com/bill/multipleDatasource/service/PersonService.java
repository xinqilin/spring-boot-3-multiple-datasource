package com.bill.multipleDatasource.service;

import com.bill.multipleDatasource.dao.PersonReadDao;
import com.bill.multipleDatasource.dao.PersonWriteDao;
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

    private final PersonWriteDao personWriteDao;
    private final PersonReadDao personReadDao;

    public PersonService(PersonWriteDao personWriteDao, PersonReadDao personReadDao) {
        this.personWriteDao = personWriteDao;
        this.personReadDao = personReadDao;
    }

    @PostConstruct
    public void init() {
        log.info("insert mock data");
        Person bill = Person.builder().name("Bill").build();
        Person sherry = Person.builder().name("Sherry").build();
        personWriteDao.save(bill);
        personWriteDao.save(sherry);
    }

    public List<Person> listPerson() {
        var result = personReadDao.findAll();
        log.info("listPerson: {}", result);
        return result;
    }

    public List<Person> addPerson(String name) {
        log.info("addPerson: {}", name);
        personWriteDao.saveAndFlush(Person.builder().name(name).build());
        var result = personWriteDao.findAll();
        log.info("addPerson: {}", result);
        return result;
    }
}
