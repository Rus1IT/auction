package com.project.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.auction.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
