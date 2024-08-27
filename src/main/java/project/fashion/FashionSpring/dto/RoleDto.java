package project.fashion.FashionSpring.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import project.fashion.FashionSpring.entity.type.RoleType;

@Getter
@Setter
public class RoleDto {
    private String roleID;
    private String roleName;
    private RoleType type;
}
