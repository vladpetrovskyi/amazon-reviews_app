package com.vp.amazonreviewsapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@EqualsAndHashCode(exclude = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GenericUser {
    @Id
    @GeneratedValue(generator = "custom-generator", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "custom-generator",
            strategy = "com.vp.amazonreviewsapp.util.idgenerator.CustomIdentifierGenerator")
    private String id;
    private String profileName;
}
