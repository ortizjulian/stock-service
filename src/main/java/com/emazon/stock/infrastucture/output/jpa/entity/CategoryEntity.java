package com.emazon.stock.infrastucture.output.jpa.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length =50)
    private String name;

    @Column(length = 90)
    private String description;

}
