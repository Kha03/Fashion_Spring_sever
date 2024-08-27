package project.fashion.FashionSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.fashion.FashionSpring.entity.type.RoleType;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private String roleID;
    @Column(name = "role_name", nullable = false)
    private String roleName;
    @Column(name = "type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType type;
    @OneToMany(mappedBy = "role")
    private Set<User> user;

    public Role(String roleID) {
        this.roleID = roleID;
    }
}
