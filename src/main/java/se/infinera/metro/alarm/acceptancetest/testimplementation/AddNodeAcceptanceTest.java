package se.infinera.metro.alarm.acceptancetest.testimplementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.infinera.metro.alarm.acceptancetest.applicationdriver.NodeApi;
import se.infinera.metro.alarm.acceptancetest.configuration.TestConfiguration;
import se.infinera.metro.service.alarm.controller.dto.NodeDTO;

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
@Component
public class AddNodeAcceptanceTest {

    private final NodeApi nodeApi;
    private final TestConfiguration testConfiguration;

    @Autowired
    public AddNodeAcceptanceTest(NodeApi nodeApi, TestConfiguration testConfiguration) {
        this.nodeApi = nodeApi;
        this.testConfiguration = testConfiguration;
    }

    public void run() {
        log.debug("Running the acceptance test...");
        log.debug("Nodes config: {}", testConfiguration.getNodesConfiguration());

        NodeDTO nodeConfig = testConfiguration.getNodesConfiguration().get(0);

        //Given
        NodeDTO nodeToAdd = NodeDTO.builder()
                .ipAddress(nodeConfig.getIpAddress())
                .port(nodeConfig.getPort())
                .userName(nodeConfig.getUserName())
                .password(nodeConfig.getPassword())
                .build();
        NodeDTO addedNode = nodeApi.addNode(nodeToAdd);
        assertNotNull(addedNode);
        assertEquals(nodeToAdd, addedNode);

        //When
        List<NodeDTO> nodes = nodeApi.getNodes();

        //Then
        assertTrue(nodes.contains(nodeToAdd));

        log.debug("SUCCESS");
    }
}
