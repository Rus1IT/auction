package root.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.auction.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
