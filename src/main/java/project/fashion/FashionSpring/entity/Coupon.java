package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fashion.FashionSpring.entity.type.CouponStatus;
import project.fashion.FashionSpring.entity.type.CouponType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private String couponID;
    @Column(name = "coupon_code", nullable = false, unique = true)
    private String couponCode;
    //type
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponType type;
    @Column(name = "description", columnDefinition = "NVARCHAR(255)")
    private String description;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(name = "min_order_amount")
    private Double minOrderAmount;
    @Column(name = "max_discount_amount")
    private Double maxDiscountAmount;
    @Column(name = "usage_limit")
    private Integer usageLimit;
    @Column(name = "used_count")
    private Integer usedCount;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    @OneToMany(mappedBy = "coupon")
    private Set<OrderCoupons> orderCoupons;


    public Coupon(String couponCode) {
        this.couponCode = couponCode;
    }
}
