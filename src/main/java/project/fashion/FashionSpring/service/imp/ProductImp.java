package project.fashion.FashionSpring.service.imp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import project.fashion.FashionSpring.dto.ProductDto;
import project.fashion.FashionSpring.entity.Product;
import project.fashion.FashionSpring.payload.request.PostPageRequest;

import java.util.List;

public interface ProductImp {
    public List<ProductDto> getAllProduct();
    public  ProductDto getProductDtoById(String id);
    public Page<ProductDto> getProductsByCriteria(String category, String name, Double minPrice, Double maxPrice, Pageable pageable);
}
