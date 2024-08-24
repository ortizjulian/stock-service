package com.emazon.stock.infrastucture.output.jpa.entity;

import com.emazon.stock.utils.Constants;
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

    @Column(unique = true, length = Constants.MAX_CHARACTERS_NAME_CATEGORY)
    private String name;

    @Column(length = Constants.MAX_CHARACTERS_DESCRIPTION_CATEGORY)
    private String description;

}
