package com.bill.multipleDatasource.dao;

import com.bill.multipleDatasource.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bill.Lin 2024/6/19
 */
@Transactional(readOnly = false)
public interface PersonWriteDao extends JpaRepository<Person, Integer> {
}
