package com.infinera.metro.alarm.acceptancetest.testimplementation;

import com.infinera.metro.alarm.AppConfig;
import com.infinera.metro.alarm.acceptancetest.applicationdriver.NodeApi;
import com.infinera.metro.alarm.acceptancetest.configuration.TestConfiguration;
import com.infinera.metro.service.alarm.controller.dto.NodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *  This class would belong to middle layer, "Test Implementation Layer"
 *
 *  TODO:
 *  - Before test, verify that no nodes are added to Alarmservice.
 *  - Remove nodes after test
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AlarmService_Nodes_Test {

    @Autowired
    private NodeApi nodeApi;
    @Autowired
    private TestConfiguration testConfiguration;

    @Test
    public void add_node_test() {
        NodeDTO nodeConfig = testConfiguration.getNodesConfiguration().get(0);

        //Given
        NodeDTO nodeToAdd = NodeDTO.builder()
                .ipAddress(nodeConfig.getIpAddress())
                .port(nodeConfig.getPort())
                .userName(nodeConfig.getUserName())
                .password(nodeConfig.getPassword())
                .build();

        NodeDTO addedNode = nodeApi.addNode(nodeToAdd);

        //When
        List<NodeDTO> nodes = nodeApi.getNodes();

        //Then
        assertNotNull(addedNode);
        assertEquals(nodeToAdd, addedNode);
        assertTrue(nodes.contains(nodeToAdd));

        log.debug("\n\n***********\n* SUCCESS *\n***********\n\n");
    }
}
