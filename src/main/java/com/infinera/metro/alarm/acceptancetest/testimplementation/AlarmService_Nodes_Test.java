package com.infinera.metro.alarm.acceptancetest.testimplementation;

import com.infinera.metro.alarm.AppConfig;
import com.infinera.metro.alarm.acceptancetest.applicationdriver.Node;
import com.infinera.metro.alarm.acceptancetest.applicationdriver.AlarmServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 *  This class would belong to middle layer, "Test Implementation Layer"
 *
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AlarmService_Nodes_Test {

    /**
     * Since a JUnit test needs exactly one public zero-argument constructor we must use
     * field injection instead of constructor injection
     */
    @Autowired
    private AlarmServiceApi alarmServiceApi;

    @Before
    public void verifyInitialState() {
        List<Node> nodesAddedToAlarmService = alarmServiceApi.getAddedNodes();
        log.debug("Verifying inital state, nodesAddedToAlarmService.isEmpty(): {}", nodesAddedToAlarmService.isEmpty());
        assertTrue(nodesAddedToAlarmService.isEmpty());
    }

    @Test
    public void addingNodeShouldResultInNodeAdded() {
        //Given
        Node addedNode = alarmServiceApi.addNode();
        //When
        List<Node> nodesAddedToAlarmService = alarmServiceApi.getAddedNodes();
        //Then
        assertTrue(nodesAddedToAlarmService.contains(addedNode));

        log.debug("Found node {}", addedNode);
        log.debug("\n\n***********\n* SUCCESS *\n***********\n\n");
    }

    @After
    public void cleanUpAfterTest() {
        alarmServiceApi.removeAllNodes();
    }
}
