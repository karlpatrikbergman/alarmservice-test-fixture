package se.infinera.metro.alarm.applicationdriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import se.infinera.metro.service.alarm.controller.dto.NodeDTO;

import java.util.List;

/**
 *  This class would belong to lowest layer, "Application Driver Layer".
 *  The API for this layer should be expressed in domain language.
 *  With a well-designed application driver layer, it becomes possible to
 *  completely dispense with the acceptance criteria layer and express the
 *  acceptance criteria in the implementation of the test.
 */
public class NodeApi {

    private final String alarmServiceHost = "localhost";
    private final long alarmServicePort = 8080;

    @Autowired
    RestTemplate restTemplate;

    public List<NodeDTO> getNodes() {
        return restTemplate.exchange(
                getAlarmServiceNodesUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NodeDTO>>() {
                }
        )
                .getBody();
    }

    public NodeDTO addNode(NodeDTO nodeDTO) {
        HttpEntity<NodeDTO> request = new HttpEntity<>(nodeDTO);
        return restTemplate.postForObject(
                getAlarmServiceNodesUri(),
                request,
                NodeDTO.class);
    }

    private String getAlarmServiceNodesUri() {
        return getAlarmServiceHostUri() + "/nodes";
    }

    private String getAlarmServiceHostUri() {
        return "http://" + alarmServiceHost +":" + alarmServicePort;
    }
}
