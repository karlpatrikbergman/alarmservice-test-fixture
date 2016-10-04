package com.infinera.metro.alarm.acceptancetest.applicationdriver;

import com.infinera.metro.alarm.acceptancetest.testimplementation.AlarmServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.infinera.metro.alarm.acceptancetest.configuration.TestConfiguration;
import com.infinera.metro.service.alarm.controller.dto.NodeDTO;

import java.util.List;

/**
 *  This class would belong to lowest layer, "Application Driver Layer".
 *  The API for this layer should be expressed in domain language.
 *  With a well-designed application driver layer, it becomes possible to
 *  completely dispense with the acceptance criteria layer and express the
 *  acceptance criteria in the implementation of the test.
 */
@Slf4j
@Component
public class NodeApi {
    private final RestTemplate restTemplate;
    private final String alarmServiceHost;
    private final long alarmServicePort;

    @Autowired
    public NodeApi(RestTemplate restTemplate, TestConfiguration testConfiguration) {
        this.restTemplate = restTemplate;
        AlarmServiceDTO alarmServiceConfig = testConfiguration.getAlarmServiceConfiguration();
        this.alarmServiceHost = alarmServiceConfig.getHost();
        this.alarmServicePort = alarmServiceConfig.getPort();
    }

    public List<NodeDTO> getNodes() {
        return restTemplate.exchange(
                    getAlarmServiceNodesUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<NodeDTO>>() {}
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
