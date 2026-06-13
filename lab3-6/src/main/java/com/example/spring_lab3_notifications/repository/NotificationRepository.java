package com.example.spring_lab3_notifications.repository;

import com.example.spring_lab3_notifications.model.entity.Notification;
import com.example.spring_lab3_notifications.model.enums.NotificationChannel;
import com.example.spring_lab3_notifications.model.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatus(NotificationStatus status);
    List<Notification> findByChannel(NotificationChannel channel);
    List<Notification> findByRecipientId(Long recipientId);

    // Часть 6.1 — поиск по двум параметрам
    List<Notification> findByStatusAndChannel(NotificationStatus status, NotificationChannel channel);

    // Часть 6.2 — сортировка по дате
    List<Notification> findByStatusOrderByCreatedAtAsc(NotificationStatus status);

    // Часть 6.3 — JPQL запрос
    @Query("""
        select n from Notification n
        where n.status = :status and n.channel = :channel
        """)
    List<Notification> findByStatusAndChannelCustom(
            @Param("status") NotificationStatus status,
            @Param("channel") NotificationChannel channel);

    // Часть 6.3 — Native SQL запрос
    @Query(value = """
        select * from notifications
        where status = :status and channel = :channel
        """, nativeQuery = true)
    List<Notification> findNativeByStatusAndChannel(
            @Param("status") String status,
            @Param("channel") String channel);
}