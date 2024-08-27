package project.fashion.FashionSpring.controller;

import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.fashion.FashionSpring.dto.ProductDto;
import project.fashion.FashionSpring.entity.Product;
import project.fashion.FashionSpring.payload.ResponData;
import project.fashion.FashionSpring.payload.request.PostPageRequest;
import project.fashion.FashionSpring.service.imp.ProductImp;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductImp productImp;

    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        ResponData responData = new ResponData();

        try {
            responData.setData(productImp.getAllProduct());
            responData.setDescription("Get all products success");
            responData.setStatus(200);  // HTTP Status OK
            return new ResponseEntity<>(responData, HttpStatus.OK);
        } catch (Exception e) {
            responData.setDescription("Get all products failed: " + e.getMessage());
            responData.setStatus(500);  // HTTP Status Internal Server Error
            return new ResponseEntity<>(responData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        ResponData responData = new ResponData();

        try {
            Optional<ProductDto> product = Optional.ofNullable(productImp.getProductDtoById(id));
            if (product.isPresent()) {
                responData.setData(product.get());
                responData.setDescription("Get product detail success");
                responData.setStatus(200);  // HTTP Status OK
                return new ResponseEntity<>(responData, HttpStatus.OK);
            } else {
                responData.setDescription("Product not found");
                responData.setStatus(404);  // HTTP Status Not Found
                return new ResponseEntity<>(responData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            responData.setDescription("Get product detail failed: " + e.getMessage());
            responData.setStatus(500);  // HTTP Status Internal Server Error
            return new ResponseEntity<>(responData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsByCriteria(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String color,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        ResponData responData = new ResponData();

        try {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
            PostPageRequest pageable = null;
            if (sortField != null) {
                pageable = new PostPageRequest(size, page, sort);
            } else {
                pageable = new PostPageRequest(size, page);
            }
            Page<ProductDto> products = productImp.getProductsByCriteria(category, color, minPrice, maxPrice, pageable);
            responData.setData(products.getContent());
            responData.setDescription("Get products by criteria success");
            responData.setStatus(200);  // HTTP Status OK
            return new ResponseEntity<>(responData, HttpStatus.OK);
        } catch (Exception e) {
            responData.setDescription("Get products by criteria failed: " + e.getMessage());
            responData.setStatus(500);  // HTTP Status Internal Server Error
            return new ResponseEntity<>(responData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
