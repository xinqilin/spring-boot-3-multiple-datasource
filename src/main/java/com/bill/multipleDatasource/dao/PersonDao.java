package com.bill.multipleDatasource.dao;

import com.bill.multipleDatasource.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bill.Lin 2024/6/19
 */
@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {
}
