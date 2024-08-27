package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private String categoryID;

    @Column(name = "name" , nullable = false, columnDefinition = "NVARCHAR(255)")
    private String categoryName;

    @Column(name = "is_parent", nullable = false)
    private boolean isParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
