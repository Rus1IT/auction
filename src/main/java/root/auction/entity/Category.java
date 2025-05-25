package root.auction.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Long categoryId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Auction> auctions = new HashSet<>();
}
