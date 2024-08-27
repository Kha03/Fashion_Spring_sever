package project.fashion.FashionSpring.service;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.fashion.FashionSpring.dto.ProductDto;
import project.fashion.FashionSpring.entity.Category;
import project.fashion.FashionSpring.entity.Product;
import project.fashion.FashionSpring.entity.ProductVariant;
import project.fashion.FashionSpring.repository.ProductRepository;
import project.fashion.FashionSpring.service.imp.ProductImp;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductImp {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertDto).toList();
    }

    @Override
    public ProductDto getProductDtoById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        return convertDto(product);
    }
    @Override
    public Page<ProductDto> getProductsByCriteria(String category, String color, Double minPrice, Double maxPrice, Pageable pageable) {
        // Build criteria based on input parameters
        Specification<Product> spec = Specification.where(null);

        if (category != null) {
            spec = spec.and((root, query, builder) -> {
                Join<Product, Category> categoryJoin = root.join("categories", JoinType.INNER);
                return builder.equal(categoryJoin.get("categoryName"), category);
            });        }

        if (color != null) {
            spec = spec.and((root, query, builder) -> {
                Join<Product, ProductVariant> productVariantsJoin = root.join("productVariants", JoinType.INNER);
                return builder.equal(productVariantsJoin.get("color"), color);
            });
        }

        if (minPrice != null && maxPrice != null) {
            spec = spec.and((root, query, builder) -> {
                Predicate pricePredicate = builder.between(root.get("discountedPrice"), minPrice, maxPrice);
                Predicate originalPricePredicate = builder.between(root.get("originalPrice"), minPrice, maxPrice);
                return builder.or(builder.and(builder.isNotNull(root.get("discountedPrice")), pricePredicate),
                        builder.and(builder.isNull(root.get("discountedPrice")), originalPricePredicate));
            });
        } else if (minPrice != null) {
            spec = spec.and((root, query, builder) -> {
                Predicate pricePredicate = builder.greaterThanOrEqualTo(root.get("discountedPrice"), minPrice);
                Predicate originalPricePredicate = builder.greaterThanOrEqualTo(root.get("originalPrice"), minPrice);
                return builder.or(builder.and(builder.isNotNull(root.get("discountedPrice")), pricePredicate),
                        builder.and(builder.isNull(root.get("discountedPrice")), originalPricePredicate));
            });
        } else if (maxPrice != null) {
            spec = spec.and((root, query, builder) -> {
                Predicate pricePredicate = builder.lessThanOrEqualTo(root.get("discountedPrice"), maxPrice);
                Predicate originalPricePredicate = builder.lessThanOrEqualTo(root.get("originalPrice"), maxPrice);
                return builder.or(builder.and(builder.isNotNull(root.get("discountedPrice")), pricePredicate),
                        builder.and(builder.isNull(root.get("discountedPrice")), originalPricePredicate));
            });
        }

        // Query database using the specification and pageable
        Page<Product> products = productRepository.findAll(spec, pageable);

        // Convert Page<Product> to Page<ProductDto>
        List<ProductDto> productDtos = products.getContent().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());

        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }



    public ProductDto convertDto(Product product) {
        ModelMapper modelMaper = new ModelMapper();
        return modelMaper.map(product, ProductDto.class);
    }
}
