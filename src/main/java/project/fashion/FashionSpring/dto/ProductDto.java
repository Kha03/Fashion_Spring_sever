package project.fashion.FashionSpring.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDto {
    private String productID;
    private String name;
    private String description;
    private LocalDate createDate;
    private Double originalPrice;
    private Double discountedPrice;
    private Double discountPercentage;
    private String image;

}
