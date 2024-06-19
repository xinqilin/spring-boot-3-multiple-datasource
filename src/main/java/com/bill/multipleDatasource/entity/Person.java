package com.bill.multipleDatasource.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Bill.Lin 2024/6/19
 */
@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
