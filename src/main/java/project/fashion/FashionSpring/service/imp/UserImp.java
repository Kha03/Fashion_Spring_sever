package project.fashion.FashionSpring.service.imp;

import project.fashion.FashionSpring.dto.UserDto;
import project.fashion.FashionSpring.payload.request.RegisterRequest;

public interface UserImp {
    public boolean registerUser(RegisterRequest registerRequest);

    public UserDto login(String username, String password);
}
