package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stores")
public class Store implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private String storeID;
    @Column(name = "city", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String city;
    @Column(name = "street", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String street;
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    //productVariant is a foreign key
    @OneToMany(mappedBy = "store")
    private Set<ProductVariantSores> productVariantSore;

    @OneToMany(mappedBy = "store")
    private Set<Order> orders;

    @OneToMany(mappedBy = "store")
    private Set<Employee> employees;

    public Store(String storeID) {
        this.storeID = storeID;
    }


}