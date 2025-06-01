package com.project.auction.service;

import com.project.auction.entity.Account;
import com.project.auction.entity.Auction;
import com.project.auction.entity.Bid;
import com.project.auction.repository.AccountRepository;
import com.project.auction.repository.AuctionRepository;
import com.project.auction.repository.BidRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BidService {

    private final BidRepository bidRepo;
    private final AuctionRepository auctionRepo;
    private final AccountRepository accountRepo;
    private final NotificationService notificationService;

    public Bid placeBid(Long auctionId, Long bidderId, long amount) {

        Auction auction = ServiceUtils.findOrThrow(
                auctionRepo.findById(auctionId), "Auction");

        if (!auction.isOpen()) {
            ServiceUtils.bad("Auction already closed");
        }

        if (amount <= auction.getCurrentPrice()) {
            ServiceUtils.bad("Bid must exceed current price");
        }

        Account bidder = ServiceUtils.findOrThrow(
                accountRepo.findById(bidderId), "Bidder");

        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setBidder(bidder);
        bid.setAmount(amount);

        auction.setCurrentPrice(amount);
        auction.setFirstBidAt(auction.getFirstBidAt() == null ?
                bid.getPlacedAt() : auction.getFirstBidAt());

        Bid saved = bidRepo.save(bid);

        notificationService.notifyOutbid(auction.getSeller(), auction, amount);
        return saved;
    }
}

