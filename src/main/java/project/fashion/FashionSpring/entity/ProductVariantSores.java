package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_variant_store")
public class ProductVariantSores implements Serializable {
    @EmbeddedId
    private ProductVariantStoreKeys productVariantStoreKeys;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productVariantID")
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("storeID")
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ProductVariantSores(ProductVariantStoreKeys productVariantStoreKeys) {
        this.productVariantStoreKeys = productVariantStoreKeys;
    }


}
