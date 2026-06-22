package com.bytebank.service;

import com.bytebank.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** In-app notifications and announcement board. */
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // TODO: push(), markRead(), getUnreadForUser()
}
