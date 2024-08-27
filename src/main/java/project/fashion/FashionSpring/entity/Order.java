package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fashion.FashionSpring.entity.type.OrderStatus;
import project.fashion.FashionSpring.entity.type.PaymentMethod;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private String orderID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_id")
    private Employee employee;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;
    @Column(name = "status" , length = 50)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "order")
    private Set<OrderCoupons> orderCoupons;

    @OneToOne(mappedBy = "order")
    private Payment payment;
    public Order(String orderID) {
        this.orderID = orderID;
    }
}
