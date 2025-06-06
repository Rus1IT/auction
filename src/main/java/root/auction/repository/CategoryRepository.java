package root.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.auction.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
