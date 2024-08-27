package project.fashion.FashionSpring.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponData {
    private  int status = 200;
    private String description;
    private String jwt;
    private Object data;
    private boolean success = true;
}
