package com.vp.amazonreviewsapp.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;
    private String profileName;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Role> roles;
}
