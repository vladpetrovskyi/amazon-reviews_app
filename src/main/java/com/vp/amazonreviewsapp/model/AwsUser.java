package com.vp.amazonreviewsapp.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Getter
@Setter
@Entity
@Table(name = "aws_users")
public class AwsUser extends GenericUser {
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Review> reviews;

    @Formula(value = "(select count(*) from Reviews r where r.user_id=user_id)")
    private Long reviewsSize;
}
