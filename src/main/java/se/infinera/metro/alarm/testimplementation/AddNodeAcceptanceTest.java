package se.infinera.metro.alarm.testimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import se.infinera.metro.alarm.applicationdriver.NodeApi;
import se.infinera.metro.service.alarm.controller.dto.NodeDTO;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 *  This class would belong to middle layer, "Test Implementation Layer"
 */
public class AddNodeAcceptanceTest {

    @Autowired
    NodeApi nodeApi;

    public void run() {
        System.out.println("Running the acceptance test...");
        //Given
        NodeDTO node = NodeDTO.builder()
                .ipAddress("172.21.0.2")
                .port(80)
                .userName("root")
                .password("")
                .build();
        nodeApi.addNode(node);

        //When
        List<NodeDTO> nodes = nodeApi.getNodes();

        //Then
        assertTrue(nodes.contains(node));
    }
}
