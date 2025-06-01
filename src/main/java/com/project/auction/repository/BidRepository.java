package com.project.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.auction.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
