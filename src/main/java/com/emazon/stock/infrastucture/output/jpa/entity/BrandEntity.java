package com.emazon.stock.infrastucture.output.jpa.entity;

import com.emazon.stock.utils.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = Constants.MAX_CHARACTERS_NAME_CATEGORY)
    private String name;

    @Column(length = Constants.MAX_CHARACTERS_DESCRIPTION_CATEGORY)
    private String description;

}
