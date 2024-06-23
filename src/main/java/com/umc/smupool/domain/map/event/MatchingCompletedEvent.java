package com.umc.smupool.domain.map.event;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public class MatchingCompletedEvent extends ApplicationEvent {
    private final List<Long> userIds;

    public MatchingCompletedEvent(Object source, List<Long> userIds) {
        super(source);
        this.userIds = userIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }
}