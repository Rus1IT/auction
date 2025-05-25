package root.auction.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bids")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Long bidId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bidder_id", referencedColumnName = "account_id",
            foreignKey = @ForeignKey(name = "fk_bids_account"))
    private Account bidder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auction_id", referencedColumnName = "auction_id",
            foreignKey = @ForeignKey(name = "fk_bids_auction"))
    private Auction auction;

    @Column(name = "amount", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private double amount;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "placed_at", nullable = false,
            updatable = false)
    private LocalDateTime placedAt;
}
