package project.fashion.FashionSpring.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.fashion.FashionSpring.entity.InfoUSer;
import project.fashion.FashionSpring.entity.Role;

@Getter
@Setter
public class UserDto {
    private String userID;
    private String username;
    private String password;
    private RoleDto roleDto;
    private InfoUserDto infoUserDto;
}
