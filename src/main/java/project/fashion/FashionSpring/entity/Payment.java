package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fashion.FashionSpring.entity.type.PaymentMethod;
import project.fashion.FashionSpring.entity.type.PaymentStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionID;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(name = "payment_date", nullable = false)
    private String paymentDate;
    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(name = "status", length = 50)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment(String transactionID) {
        this.transactionID = transactionID;
    }
}
