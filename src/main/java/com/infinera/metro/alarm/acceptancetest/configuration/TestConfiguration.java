package com.infinera.metro.alarm.acceptancetest.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infinera.metro.alarm.acceptancetest.testimplementation.AlarmServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import se.infinera.metro.service.alarm.controller.dto.NodeDTO;
import se.patrikbergman.java.utility.resource.ResourceString;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class TestConfiguration {
    private List<NodeDTO> nodesConfiguration;
    private AlarmServiceDTO alarmServiceConfiguration;
    private final ObjectMapper mapper;

    public TestConfiguration() {
        this.mapper = new ObjectMapper();
        initAlarmServiceConfiguration();
        initNodesConfiguration();
    }

    public List<NodeDTO> getNodesConfiguration() {
        return nodesConfiguration;
    }

    public AlarmServiceDTO getAlarmServiceConfiguration() {
        return alarmServiceConfiguration;
    }

    private void initNodesConfiguration() {
        try {
            String nodesConfig = new ResourceString("nodes_config.json").toString();
            nodesConfiguration = mapper.readValue(nodesConfig, new TypeReference<List<NodeDTO>>(){});
        } catch (IOException e) {
            log.error("Failed to read Nodes configuration config from file.", e);
        }
    }

    private void initAlarmServiceConfiguration() {
        try {
            String alarmServiceConfig = new ResourceString("alarmservice_config.json").toString();
            alarmServiceConfiguration = mapper.readValue(alarmServiceConfig, AlarmServiceDTO.class);
        } catch (IOException e) {
            log.error("Failed to read Alarm service config from file.", e);
        }
    }
}
