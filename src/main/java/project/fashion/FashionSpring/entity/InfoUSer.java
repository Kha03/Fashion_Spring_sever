package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "info_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class InfoUSer implements Serializable {
    @Id
    @Column(name = "user_id")
    private String id;

    @MapsId
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String lastName;
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "phone", length = 20, unique = true, nullable = false)
    private String phone;
    @Column(name = "address", length = 250, nullable = false, columnDefinition = "NVARCHAR(250)")
    private String address;
    @Column(name = "city", length = 100, nullable = false, columnDefinition = "NVARCHAR(100)")
    private String city;
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
    @Column(name = "image")
    private String image;

    public InfoUSer(User user) {
        this.user = user;
    }
}
