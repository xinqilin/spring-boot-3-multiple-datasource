package com.bill.multipleDatasource.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author Bill.Lin 2024/6/19
 */
@Service
@Log4j2
public class PersonService {

    @PostConstruct
    public void init() {
        log.info("insert mock data");

    }
}
