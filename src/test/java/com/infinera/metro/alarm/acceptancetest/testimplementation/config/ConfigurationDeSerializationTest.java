package com.infinera.metro.alarm.acceptancetest.testimplementation.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import com.infinera.metro.alarm.acceptancetest.testimplementation.AlarmServiceDTO;
import se.infinera.metro.service.alarm.controller.dto.NodeDTO;
import se.patrikbergman.java.utility.resource.ResourceString;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class ConfigurationDeSerializationTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void deSerializeNodesConfig() throws IOException {
        String nodesConfig = new ResourceString("nodes_config.json").toString();
        List<NodeDTO> nodes = mapper.readValue(nodesConfig, new TypeReference<List<NodeDTO>>(){});
        assertNotNull(nodes);
        System.out.println(nodes);
    }

    @Test
    public void deSerializeAlarmServiceConfig() throws IOException {
        String alarmServiceConfig = new ResourceString("alarmservice_config.json").toString();
        AlarmServiceDTO alarmServiceDTO = mapper.readValue(alarmServiceConfig, AlarmServiceDTO.class);
        assertNotNull(alarmServiceDTO);
        System.out.println(alarmServiceDTO);
    }
}
