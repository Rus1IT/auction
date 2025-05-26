package root.auction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.auction.dto.request.CreateAccountRequest;
import root.auction.repository.AccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public void createAccount(CreateAccountRequest accountRequest) {

    }
}
