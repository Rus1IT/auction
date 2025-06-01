package com.project.auction.service;

import com.project.auction.dto.request.CreateAuctionRequest;
import com.project.auction.entity.Account;
import com.project.auction.entity.Auction;
import com.project.auction.entity.Category;
import com.project.auction.enums.AuctionStatus;
import com.project.auction.repository.AuctionRepository;
import com.project.auction.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.auction.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuctionService {

    private final AuctionRepository auctionRepo;

    private final AccountService accountService;

    private final CategoryRepository categoryRepository;

    private final CategoryRepository catRepo;

    ///  Must be
    public Auction create(CreateAuctionRequest dto) {

        Account seller = accountService.getAccount();

        Set<Category> cats = new HashSet<>(
                catRepo.findAllById(dto.categoryIds()));

        if (cats.size() != dto.categoryIds().size()) {
            ServiceUtils.bad("One or more categories not found");
        }

        Auction a = new Auction();
        a.setSeller(seller);
        //a.setCategories(cats);
        a.setTitle(dto.title());
        a.setDescription(dto.description());
        a.setMinPrice(dto.minPrice());
        a.setDuration(15L);
        a.setStatus(AuctionStatus.OPEN);
        a.setCurrentPrice(dto.minPrice());

        a.getCategories().addAll(List.of());
        return auctionRepo.save(a);
    }

    ///  Must be
    @Transactional(readOnly = true)
    public Auction getAuctionById(Long auctionId) {
        return ServiceUtils.findOrThrow(auctionRepo.findById(auctionId), "Auction");
    }

    ///  Must be
    public Auction updateDescription(Long auctionId, String description){
        Account myAccount = accountService.getAccount();
        Auction auction = getAuctionById(auctionId);

        if(!auction.getSeller().equals(myAccount))
            throw new RuntimeException("You don't have permission for this");

        auction.setDescription(description);

        return auctionRepo.save(auction);
    }

    ///  Must be
    public Auction changeMinPrice(Long newMinPrice, Long auctionId){
        Account myAccount = accountService.getAccount();
        Auction auction = getAuctionById(auctionId);

        if(!auction.getSeller().equals(myAccount))
            throw new RuntimeException("You don't have permission for this");

        auction.setMinPrice(newMinPrice);

        // Save it
        return auctionRepo.save(auction);
    }

    ///  Must be
    public Auction changeFistBidsTime(LocalDateTime newFistBidAt, Long auctionId){
        Account myAccount = accountService.getAccount();
        Auction auction = getAuctionById(auctionId);

        if(!auction.getSeller().equals(myAccount))
            throw new RuntimeException("You don't have permission for this");

        if(LocalDateTime.now().isAfter(newFistBidAt)){
            throw new RuntimeException("Incorrect time : " + newFistBidAt);
        }

        auction.setFirstBidAt(newFistBidAt);

        return auctionRepo.save(auction);
    }

    public Auction changeCategories(List<Long> categories, Long auctionId){
        Account myAccount = accountService.getAccount();
        Auction auction = getAuctionById(auctionId);


        /**
         * Condemted
         */
        checkIfCategoriesIfExists();

        if(!auction.getSeller().equals(myAccount))
            throw new RuntimeException("You don't have permission for this");


    }

    private void checkIfCategoriesIfExists(List<Long> categories){
        for(Long v : categories){
            ServiceUtils.findOrThrow(categoryRepository.findById(v), "Category with id : " + v + " not found ");
        }
    }

    /**
     *  There must be implemented a sheduler to change status of Auction
     *
     *
     * */
//    /** Called by a scheduler or directly when bids cease */
//    public void close(Long id) {
//        Auction a = get(id);
//        a.setStatus(AuctionStatus.CLOSED);
//    }
}
