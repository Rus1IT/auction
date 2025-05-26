package root.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.auction.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
