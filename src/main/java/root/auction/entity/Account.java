package root.auction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import root.auction.enums.AccountType;

import java.time.LocalDateTime;
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
    private Long accountId;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    @NotNull
    @Column(name = "password_hash", nullable = false, updatable = false)
    private String passwordHash;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "account_type")
    private AccountType accountType;
}
