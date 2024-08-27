package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_coupons")
public class OrderCoupons implements Serializable {
    @EmbeddedId
    private OrderCouponKeys orderCouponKeys;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderID")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("couponID")
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(name = "discount_applied", nullable = false)
    private double discountApplied;

    public OrderCoupons(OrderCouponKeys orderCouponKeys) {
        this.orderCouponKeys = orderCouponKeys;
    }
}
