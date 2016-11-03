package com.infinera.metro.alarm.acceptancetest.mapping;

import com.infinera.metro.alarm.acceptancetest.applicationdriver.Node;
import com.infinera.metro.alarm.acceptancetest.applicationdriver.mapping.NodeMapper;
import com.infinera.metro.service.alarm.controller.dto.NodeDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NodeMapperTest {
    private NodeMapper nodeMapper;
    private Node nodeSource;
    private NodeDTO nodeDTOSource;

    @Before
    public void setup() {
        nodeMapper = NodeMapper.INSTANCE;
    }

    @Test
    public void nodeToNodeDTO() {
        Node nodeSource =  createNode();
        NodeDTO nodeDTO = nodeMapper.toNodeDTO(nodeSource);
        assertNotNull(nodeDTO);
        assertEquals(nodeSource.getIpAddress(), nodeDTO.getIpAddress());
        assertEquals(nodeSource.getPort(), nodeDTO.getPort());
        assertEquals(nodeSource.getUserName(), nodeDTO.getUserName());
        assertEquals(nodeSource.getPassword(), nodeDTO.getPassword());
    }

    @Test
    public void nodeDTOToNode() {
        NodeDTO nodeDTOSource =  createNodeDTO();
        Node node = nodeMapper.toNode(nodeDTOSource);
        assertNotNull(node);
        assertEquals(nodeDTOSource.getIpAddress(), node.getIpAddress());
        assertEquals(nodeDTOSource.getPort(), node.getPort());
        assertEquals(nodeDTOSource.getUserName(), node.getUserName());
        assertEquals(nodeDTOSource.getPassword(), node.getPassword());
    }

    @Test
    public void nodeListToNodeDTOList() {
        List<Node> nodeListSource = createNodeList();
        List<NodeDTO> nodeDTOList = nodeMapper.toNodeDTOList(nodeListSource);
        assertNotNull(nodeDTOList);
        assertEquals(nodeListSource.size(), nodeDTOList.size());
        assertEquals(nodeListSource.get(0).getIpAddress(), nodeDTOList.get(0).getIpAddress());
    }

    @Test
    public void nodeDTOListToNodeList() {
        List<NodeDTO> nodeDTOListSource = createNodeDTOList();
        List<Node> nodeList = nodeMapper.toNodeList(nodeDTOListSource);
        assertNotNull(nodeList);
        assertEquals(nodeDTOListSource.size(), nodeList.size());
        assertEquals(nodeDTOListSource.get(0).getIpAddress(), nodeList.get(0).getIpAddress());
    }

    private List<Node> createNodeList() {
        return Arrays.asList(createNode());
    }

    private Node createNode() {
        return nodeSource = Node.builder()
                .ipAddress("11.22.33.44")
                .port(8080)
                .userName("user")
                .password("password")
                .build();
    }

    private List<NodeDTO> createNodeDTOList() {
        return Arrays.asList(createNodeDTO());
    }

    private NodeDTO createNodeDTO() {
        return nodeDTOSource = NodeDTO.builder()
                .ipAddress("44.55.66.77")
                .port(9090)
                .userName("john")
                .password("doe")
                .build();
    }
}
