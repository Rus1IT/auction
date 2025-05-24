package root.auction.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "auctions")
@Getter
@Setter
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    @Setter(AccessLevel.NONE)
    private Long accountId = null;

//    @ManyToOne(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
//    private Account profile;
}
