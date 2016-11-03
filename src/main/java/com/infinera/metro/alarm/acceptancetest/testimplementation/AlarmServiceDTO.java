package com.infinera.metro.alarm.acceptancetest.testimplementation;

import lombok.*;


@AllArgsConstructor(access = AccessLevel.PUBLIC) //Needed by Orika
@Value
@Builder
public class AlarmServiceDTO {
    @NonNull
    private String host;
    private int port;
}

