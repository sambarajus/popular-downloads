package com.ssk.challenge.jgrog;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ArtifactServiceTest {

    ArtifactService artifactService = new ArtifactService();
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    APIInvoker apiInvoker;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        artifactService.setApiInvoker(apiInvoker);
    }

    @Test
    public void testGetPopularDownloads() throws Exception {

        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
        when(apiInvoker.getArtifacts(anyString())).thenReturn(getArtifacts());
        when(apiInvoker.getArtifactStatistics(arg.capture(), arg.capture(), arg.capture())).thenAnswer((InvocationOnMock invocation) -> getArtifactStatistics((String) invocation.getArguments()[2]));
        List<Artifact> popularDownloads = artifactService.getPopularDownloads(2, "generic-local");
        System.out.println("Popular Downloads::::");
        popularDownloads.forEach(artifact -> System.out.println(artifact));
    }

    //Helper methods to fetch listOfArtifacts can be replaced with Rest API call.
    private JsonNode getArtifacts() throws Exception {
        String artifactsJson = "{\"results\":[{\"repo\":\"generic-local\",\"path\":\".\",\"name\":\"multi3-1.war\",\"type\":\"file\",\"size\":2749678,\"created\":\"2017-09-25T21:17:48.691Z\",\"created_by\":\"admin\",\"modified\":\"2017-09-25T21:17:46.000Z\",\"modified_by\":\"admin\",\"updated\":\"2017-09-25T21:17:46.000Z\"},{\"repo\":\"generic-local\",\"path\":\".\",\"name\":\"multi3-2.war\",\"type\":\"file\",\"size\":2749678,\"created\":\"2017-09-25T21:17:48.691Z\",\"created_by\":\"admin\",\"modified\":\"2017-09-25T21:17:46.000Z\",\"modified_by\":\"admin\",\"updated\":\"2017-09-25T21:17:46.000Z\"},{\"repo\":\"generic-local\",\"path\":\".\",\"name\":\"multi3-3.war\",\"type\":\"file\",\"size\":2749678,\"created\":\"2017-09-25T21:17:48.691Z\",\"created_by\":\"admin\",\"modified\":\"2017-09-25T21:17:46.000Z\",\"modified_by\":\"admin\",\"updated\":\"2017-09-25T21:17:46.000Z\"},{\"repo\":\"generic-local\",\"path\":\".\",\"name\":\"multi3-4.war\",\"type\":\"file\",\"size\":2749678,\"created\":\"2017-09-25T21:17:48.691Z\",\"created_by\":\"admin\",\"modified\":\"2017-09-25T21:17:46.000Z\",\"modified_by\":\"admin\",\"updated\":\"2017-09-25T21:17:46.000Z\"},{\"repo\":\"generic-local\",\"path\":\".\",\"name\":\"multi3-5.war\",\"type\":\"file\",\"size\":2749678,\"created\":\"2017-09-25T21:17:48.691Z\",\"created_by\":\"admin\",\"modified\":\"2017-09-25T21:17:46.000Z\",\"modified_by\":\"admin\",\"updated\":\"2017-09-25T21:17:46.000Z\"}],\"range\":{\"start_pos\":0,\"end_pos\":1,\"total\":1}}";
        JsonNode resultNode = mapper.readTree(artifactsJson);
        return resultNode.get("results");
    }

    //Helper method to fetch statistics.. can be replaced with Rest API call.
    private JsonNode getArtifactStatistics(String artifactName) throws Exception {
        Map<String, String> statsmap = new HashMap();
        System.out.println(artifactName);
        statsmap.put("multi3-1.war", "{\"uri\":\"http://35.224.254.203:8083/artifactory/generic-local/multi3-1.war\",\"downloadCount\":5,\"lastDownloaded\":0,\"remoteDownloadCount\":0,\"remoteLastDownloaded\":0}");
        statsmap.put("multi3-2.war", "{\"uri\":\"http://35.224.254.203:8083/artifactory/generic-local/multi3-2.war\",\"downloadCount\":15,\"lastDownloaded\":0,\"remoteDownloadCount\":0,\"remoteLastDownloaded\":0}");
        statsmap.put("multi3-3.war", "{\"uri\":\"http://35.224.254.203:8083/artifactory/generic-local/multi3-3.war\",\"downloadCount\":10,\"lastDownloaded\":0,\"remoteDownloadCount\":0,\"remoteLastDownloaded\":0}");
        statsmap.put("multi3-4.war", "{\"uri\":\"http://35.224.254.203:8083/artifactory/generic-local/multi3-4.war\",\"downloadCount\":12,\"lastDownloaded\":0,\"remoteDownloadCount\":0,\"remoteLastDownloaded\":0}");
        statsmap.put("multi3-5.war", "{\"uri\":\"http://35.224.254.203:8083/artifactory/generic-local/multi3-5.war\",\"downloadCount\":0,\"lastDownloaded\":0,\"remoteDownloadCount\":0,\"remoteLastDownloaded\":0}");
        statsmap.put("multi3-6.war", "{\"uri\":\"http://35.224.254.203:8083/artifactory/generic-local/multi3-6.war\",\"downloadCount\":4,\"lastDownloaded\":0,\"remoteDownloadCount\":0,\"remoteLastDownloaded\":0}");

        return mapper.readTree(statsmap.get(artifactName));
    }
}