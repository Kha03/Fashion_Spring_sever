package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fashion.FashionSpring.entity.type.Size;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_variants")
public class ProductVariant implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_variant_id")
    private String productVariantID;
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;
    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column(name = "color", nullable = false, columnDefinition = "NVARCHAR(35)")
    private String color;
    //product is a foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    // store is a foreign key
    @OneToMany(mappedBy = "productVariant")
    private Set<ProductVariantSores> productVariantSore;
    //orderDetail is a foreign key
    @OneToMany(mappedBy = "productVariant")
    private Set<OrderDetail> orderDetails;
    //subImage is a collection of sub images
    @ElementCollection
    @CollectionTable(name = "sub_images", joinColumns = @JoinColumn(name = "product_variant_id"))
    @Column(name = "sub_image", nullable = false)
    private Set<String> subImage;


    public ProductVariant(String productVariantID) {
        this.productVariantID = productVariantID;
    }

}
