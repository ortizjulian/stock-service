package com.emazon.stock.infrastucture.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length =50)
    private String name;

    @Column(length = 90)
    private String description;

}
