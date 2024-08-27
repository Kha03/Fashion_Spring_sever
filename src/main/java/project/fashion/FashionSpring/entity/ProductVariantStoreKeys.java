package project.fashion.FashionSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProductVariantStoreKeys implements Serializable {
    @Column(name = "product_variant_id")
    private String productVariantID;
    @Column(name = "store_id")
    private String storeID;
}
