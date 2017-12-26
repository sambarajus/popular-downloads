package com.ssk.challenge.jgrog;

import com.fasterxml.jackson.databind.JsonNode;
import junit.framework.TestCase;
import org.junit.Test;

public class APIInvokerTest extends TestCase {
    APIInvoker apiInvoker = new APIInvoker();
    public void testGetArtifactStatistics() throws Exception {
       JsonNode artifactNode =  apiInvoker.getArtifactStatistics("generic-local", ".", "multi3-6.12-20170516.002032-1.war");
       assertNotNull(artifactNode);
        assertTrue(artifactNode.get("uri").asText().contains("multi3-6.12-20170516.002032-1.war"));
    }

    @Test
    public void testGetArtifacts() throws Exception {
        JsonNode artifactsNode = apiInvoker.getArtifacts("generic-local");
        assertNotNull(artifactsNode);
        System.out.println(artifactsNode.get(0));
        assertEquals("generic-local", artifactsNode.get(0).get("repo").asText());
    }

    @Test
    public void testGenerateAccessToken() throws Exception {
        assertNotNull(apiInvoker.generateAccessToken());
    }

}