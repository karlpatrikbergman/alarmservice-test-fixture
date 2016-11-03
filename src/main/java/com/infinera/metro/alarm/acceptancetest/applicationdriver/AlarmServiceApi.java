package com.infinera.metro.alarm.acceptancetest.applicationdriver;

import com.infinera.metro.alarm.acceptancetest.applicationdriver.mapping.NodeMapper;
import com.infinera.metro.alarm.acceptancetest.configuration.TestConfiguration;
import com.infinera.metro.service.alarm.controller.dto.NodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  This class would belong to lowest layer, "Application Driver Layer".
 *  The API for this layer should be expressed in domain language.
 *  With a well-designed application driver layer, it becomes possible to
 *  completely dispense with the acceptance criteria layer and express the
 *  acceptance criteria in the implementation of the test.
 */
@Slf4j
@Component
public class AlarmServiceApi {
    private final RestTemplate restTemplate;
    private final TestConfiguration testConfiguration;
    private final NodeMapper nodeMapper = NodeMapper.INSTANCE;

    @Autowired
    public AlarmServiceApi(RestTemplate restTemplate, TestConfiguration testConfiguration) {
        this.restTemplate = restTemplate;
        this.testConfiguration = testConfiguration;
    }

    public Node addNode() {
        HttpEntity<NodeDTO> request = new HttpEntity<>(defaultNodeDTO());
        NodeDTO addedNodeDTO = restTemplate.postForObject(
                getAlarmServiceNodesUri(),
                request,
                NodeDTO.class);
        return nodeMapper.toNode(addedNodeDTO);
    }

    public void removeNode(String nodeIpAddress) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ipAddress", nodeIpAddress);
        restTemplate.delete(getAlarmServiceNodesUri() + "/{ipAddress}",  params);
    }

    public List<Node> getAddedNodes() {
        List<NodeDTO> addedNodeDTOs = restTemplate.exchange(
                    getAlarmServiceNodesUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<NodeDTO>>() {}
                )
            .getBody();
        return nodeMapper.toNodeList(addedNodeDTOs);
    }

    public void removeAllNodes() {
        List<NodeDTO> addedNodeDTOs = restTemplate.exchange(
                    getAlarmServiceNodesUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<NodeDTO>>() {}
                )
            .getBody();
        addedNodeDTOs.forEach(
                nodeDTO -> removeNode(nodeDTO.getIpAddress())
        );
    }

    private NodeDTO defaultNodeDTO() {
        NodeDTO nodeConfig = testConfiguration.getNodesConfiguration().get(0);
        return  NodeDTO.builder()
                .ipAddress(nodeConfig.getIpAddress())
                .port(nodeConfig.getPort())
                .userName(nodeConfig.getUserName())
                .password(nodeConfig.getPassword())
                .build();
    }

    private String getAlarmServiceNodesUri() {
        return getAlarmServiceHostUri() + "/nodes";
    }

    private String getAlarmServiceHostUri() {
        final String alarmServiceHost = testConfiguration.getAlarmServiceConfiguration().getHost();
        final int alarmServicePort = testConfiguration.getAlarmServiceConfiguration().getPort();
        return "http://" + alarmServiceHost +":" + alarmServicePort;
    }
}
