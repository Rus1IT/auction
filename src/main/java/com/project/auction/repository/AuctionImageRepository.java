package com.project.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.auction.entity.AuctionImage;

public interface AuctionImageRepository extends JpaRepository<AuctionImage, Long> {
}
