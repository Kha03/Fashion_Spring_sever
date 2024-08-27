package project.fashion.FashionSpring.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "staffs")
@DiscriminatorValue("EMPLOYEE")
public class Employee extends InfoUSer implements Serializable {
    @OneToMany(mappedBy = "employee")
    private Set<Order> orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Employee() {
    }

}
