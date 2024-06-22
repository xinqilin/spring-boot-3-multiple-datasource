package com.bill.multipleDatasource.service;

import com.bill.multipleDatasource.dao.PersonDao;
import com.bill.multipleDatasource.entity.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bill.Lin 2024/6/22
 */
@Service
@Log4j2
@Transactional(readOnly = true)
public class PersonReadService {

    private final PersonDao personDao;

    public PersonReadService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> listPerson() {
        var result = personDao.findAll();
        log.info("listPerson: {}", result);
        return result;
    }
}
