package com.bill.multipleDatasource.dao;

import com.bill.multipleDatasource.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bill.Lin 2024/6/19
 */
public interface PersonWriteDao extends JpaRepository<Person, Integer> {
}
