package root.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.auction.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
