package project.fashion.FashionSpring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import project.fashion.FashionSpring.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> , JpaSpecificationExecutor<Product> {
    @Override
    Page<Product> findAll(Pageable pageable);

}
