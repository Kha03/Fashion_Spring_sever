package project.fashion.FashionSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.fashion.FashionSpring.entity.InfoUSer;
import project.fashion.FashionSpring.entity.InfoUserId;
import project.fashion.FashionSpring.entity.User;

@Repository
public interface InfoUserRepository extends JpaRepository<InfoUSer, String> {
}
