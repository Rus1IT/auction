package com.project.auction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.project.auction.enums.AuctionStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auctions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id", updatable = false)
    @Setter(AccessLevel.NONE)
    private Long auctionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_auction_seller"))
    private Account seller;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionImage> images = new ArrayList<>();

    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(name = "min_price", nullable = false)
    private Long minPrice;

    @NotNull
    @Column(name = "current_price", nullable = false)
    private Long currentPrice;

    @Column(name = "status", nullable = false, columnDefinition = "auction_status")
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    @ManyToMany
    @JoinTable(name = "categories", joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Column(name = "first_bid_at")
    private LocalDateTime firstBidAt;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;


    public boolean isOpen(){
        return status.equals(AuctionStatus.OPEN);
    }
}
