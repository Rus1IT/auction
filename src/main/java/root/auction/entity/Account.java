package root.auction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import root.auction.enums.AccountType;

import java.util.Date;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    @Setter(AccessLevel.NONE)
    private Long userId;

    @NotNull
    @Column(name = "email", nullable = false, unique = true, updatable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "password_hash", nullable = false, updatable = false)
    private String passwordHash;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @NotNull
    @Column(name = "type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

}
