package project.fashion.FashionSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.fashion.FashionSpring.dto.UserDto;
import project.fashion.FashionSpring.payload.ResponData;
import project.fashion.FashionSpring.payload.request.RegisterRequest;
import project.fashion.FashionSpring.security.CustomUserDetails;
import project.fashion.FashionSpring.service.imp.UserImp;
import project.fashion.FashionSpring.utils.JwtTokenProvider;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserImp userImp;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        SecurityContextHolder.getdaContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());

        UserDto userDto = userImp.login(loginRequest.username(), loginRequest.password());
        ResponData responData = new ResponData();
        if (userDto != null) {
            responData.setData(userDto);
            responData.setJwt(jwt);
            responData.setDescription("Login success");
            responData.setStatus(200);  // HTTP Status OK
            return new ResponseEntity<>(responData, HttpStatus.OK);
        } else {
            responData.setDescription("Login failed");
            responData.setStatus(500);  // HTTP Status Internal Server Error
            return new ResponseEntity<>(responData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public record LoginRequest(String username, String password) {
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        ResponData responData = new ResponData();
        if (userImp.registerUser(registerRequest)) {
            responData.setData(true);
            responData.setDescription("register success");
            responData.setStatus(200);  // HTTP Status OK
            return new ResponseEntity<>(responData, HttpStatus.OK);
        } else {
            responData.setDescription("register failed");
            responData.setStatus(500);  // HTTP Status Internal Server Error
            return new ResponseEntity<>(responData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
