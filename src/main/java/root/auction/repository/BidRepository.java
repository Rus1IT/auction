package root.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.auction.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
