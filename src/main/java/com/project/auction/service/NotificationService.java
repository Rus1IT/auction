package com.project.auction.service;

import com.project.auction.entity.Account;
import com.project.auction.entity.Auction;
import com.project.auction.entity.Notification;
import com.project.auction.enums.NotificationType;
import com.project.auction.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notifRepo;

    public Notification notifyOutbid(Account seller, Auction auction, long newPrice) {
        String text = "Your auction “%s” was outbid: %d ₸".formatted(
                auction.getTitle(), newPrice);

        Notification n = new Notification();
        n.setAccount(seller);
        n.setAuction(auction);
        n.setType(NotificationType.OUTBID);
        n.setText(text);

        return notifRepo.save(n);
    }

    public void markAsRead(Long notificationId) {
        Notification n = ServiceUtils.findOrThrow(
                notifRepo.findById(notificationId), "Notification");
        n.setReadedAt(java.time.LocalDateTime.now());
    }
}
