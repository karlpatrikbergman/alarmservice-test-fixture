package se.infinera.metro.alarm.testimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import se.infinera.metro.alarm.applicationdriver.NodeApi;

/**
 *  This class would belong to middle layer, "Test Implementation Layer"
 */
public class AddNodeAcceptanceTest {

    @Autowired
    NodeApi nodeApi;

    public void run() {
        System.out.println("Running the acceptance test...");
//        //Given
//        NodeDTO node = NodeDTO.builder()
//                .ipAddress("172.21.0.4")
//                .port(80)
//                .userName("root")
//                .password("")
//                .build();
//        nodeApi.addNode(node);
//
//        //When
//        List<NodeDTO> nodes = nodeApi.getNodes();
//
//        //Then
//        assertTrue(nodes.contains(node));
    }
}
