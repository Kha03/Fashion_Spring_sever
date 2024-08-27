package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private String productID;
    @Column(name = "name", nullable = false, length = 255, columnDefinition = "NVARCHAR(255)")
    private String name;
    @Column(name = "description", columnDefinition = "NVARCHAR(255)")
    private String description;
    @Column(name = "create_date", nullable = false)
        private LocalDate createDate;
    @Column(name = "original_price", nullable = false)
    private double originalPrice;
    @Column(name = "discounted_price")
    private Double discountedPrice;
    @Column(name = "discount_percentage")
    private Double discountPercentage;
    @Column(name = "image")
    private String image;

    // Category is a foreign key
    @ManyToMany
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    //product_variants
    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> productVariants;

    public Product(String productID) {
        this.productID = productID;
    }

}
