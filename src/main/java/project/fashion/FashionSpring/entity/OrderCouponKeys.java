package project.fashion.FashionSpring.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class OrderCouponKeys implements Serializable {
    @Column(name = "order_id")
    private String orderID;
    @Column(name = "coupon_id")
    private String couponID;
}
