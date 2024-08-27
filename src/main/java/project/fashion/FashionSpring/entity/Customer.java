package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@DiscriminatorValue("CUSTOMER")
public class Customer extends InfoUSer implements Serializable {

   @OneToMany(mappedBy = "customer")
    private Set<Order> orders;


}
