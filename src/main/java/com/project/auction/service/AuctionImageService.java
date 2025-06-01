package com.project.auction.service;

import com.project.auction.entity.Auction;
import com.project.auction.entity.AuctionImage;
import com.project.auction.repository.AuctionImageRepository;
import com.project.auction.repository.AuctionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionImageService {

    private final AuctionRepository auctionRepo;
    private final AuctionImageRepository imageRepo;

    ///  Must be
    public AuctionImage addImage(Long auctionId, String urlToImage) {
        Auction auction = ServiceUtils.findOrThrow(
                auctionRepo.findById(auctionId), "Auction");

        AuctionImage img = new AuctionImage();
        img.setAuction(auction);
        img.setImageUrl(urlToImage);
        return imageRepo.save(img);
    }

    ///  Must be
    public void delete(Long imageId) {
        imageRepo.deleteById(imageId);
    }

    ///  Must be
    public List<AuctionImage> getAll(){
        return imageRepo.findAll();
    }
}
