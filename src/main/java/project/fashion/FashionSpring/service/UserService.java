package project.fashion.FashionSpring.service;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.fashion.FashionSpring.dto.InfoUserDto;
import project.fashion.FashionSpring.dto.RoleDto;
import project.fashion.FashionSpring.dto.UserDto;
import project.fashion.FashionSpring.entity.InfoUSer;
import project.fashion.FashionSpring.entity.Role;
import project.fashion.FashionSpring.entity.User;
import project.fashion.FashionSpring.payload.request.RegisterRequest;
import project.fashion.FashionSpring.repository.InfoUserRepository;
import project.fashion.FashionSpring.repository.UserRepository;
import project.fashion.FashionSpring.service.imp.UserImp;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService implements UserImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InfoUserRepository infoUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean registerUser(RegisterRequest registerRequest) {
       if(userRepository.existsByUsername(registerRequest.getUsername()))
           throw new RuntimeException("Username already exists!");
        User user = new User();
        user.setUserID(registerRequest.getUserID());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Role role = new Role();
        role.setRoleID(registerRequest.getRoleID());
        user.setRole(role);
        try {
//            userRepository.save(user);
            InfoUSer infoUSer = new InfoUSer();
//            infoUSer.setId(registerRequest.getUserID());
            infoUSer.setUser(user);
            infoUSer.setFirstName(registerRequest.getFirstName());
            infoUSer.setLastName(registerRequest.getLastName());
            infoUSer.setPhone(registerRequest.getPhone());
            infoUSer.setPhone(registerRequest.getPhone());
            infoUSer.setAddress(registerRequest.getAddress());
            LocalDate localDate = LocalDate.now();
            infoUSer.setEmail(registerRequest.getEmail());
            infoUSer.setCreatedDate(localDate);
            infoUSer.setCity(registerRequest.getCity());
            infoUSer.setImage(registerRequest.getImage());
            infoUserRepository.save(infoUSer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserDto login(String username, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Username or password is incorrect!");
        }
        User user = optionalUser.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return convertDto(user);
        }
        return null;
    }

    public UserDto convertDto(User user) {
        ModelMapper modelMaper = new ModelMapper();
        RoleDto roleDto = modelMaper.map(user.getRole(), RoleDto.class);
        InfoUserDto infoUserDto = modelMaper.map(user.getInfoUSer(), InfoUserDto.class);
        UserDto userDto = modelMaper.map(user, UserDto.class);
        userDto.setRoleDto(roleDto);
        userDto.setInfoUserDto(infoUserDto);
        return userDto;
    }
}
