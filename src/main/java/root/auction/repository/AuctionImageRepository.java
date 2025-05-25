package root.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.auction.entity.AuctionImage;

public interface AuctionImageRepository extends JpaRepository<AuctionImage, Long> {
}
