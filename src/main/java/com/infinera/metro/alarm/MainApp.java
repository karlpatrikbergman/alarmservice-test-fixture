package com.infinera.metro.alarm;

import com.infinera.metro.alarm.acceptancetest.testimplementation.AlarmService_Nodes_Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(AlarmService_Nodes_Test.class.getName());
    }
}
