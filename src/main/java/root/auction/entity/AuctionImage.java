package root.auction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "auction_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "auction")
public class AuctionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auction_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_image_auction"))
    private Auction auction;

    @NotBlank
    @Size(max = 255)
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
}
