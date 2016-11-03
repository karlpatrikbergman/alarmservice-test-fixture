package com.infinera.metro.alarm.acceptancetest.applicationdriver;

import lombok.*;

/**
 * Class derived from Alarm-service domain, part of Domain Specific Language for Alarm-service
 * functional acceptance tests
 */

@AllArgsConstructor(access = AccessLevel.PUBLIC) //Needed by Orika
@Value
@Builder
public class Node {
    @NonNull
    private final String ipAddress;
    private final int port;
    @NonNull
    private final String userName;
    @NonNull
    private final String password;
}
