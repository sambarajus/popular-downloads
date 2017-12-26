package com.ssk.challenge.jgrog;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class ArtifactService {


    private APIInvoker apiInvoker = new APIInvoker();
    private ObjectMapper mapper = new ObjectMapper();

    List<Artifact> getPopularDownloads(int mostPopularCount, String repoName) {

        Map<String, Artifact> artifactoryMap = new HashMap<String, Artifact>();

        List<Artifact> artifactories = getArtifacts(repoName);
        artifactories.forEach(a -> {
            artifactoryMap.put(a.getName(), getArtifactStatistics(a));
        });

        Queue<Artifact> priorityQueue = new PriorityQueue<>(idComparator);
        for (String aName : artifactoryMap.keySet()) {
            priorityQueue.add(artifactoryMap.get(aName));
        }

        List<Artifact> artifactsByCount = new ArrayList<>();
        for (int i = 0; i < mostPopularCount; i++) {
            artifactsByCount.add(priorityQueue.poll());
        }
        return artifactsByCount;
    }


    private List<Artifact> getArtifacts(String repoName) {
        List<Artifact> artifacts = new ArrayList<>();
        JsonNode responseNode = apiInvoker.getArtifacts(repoName);
        if (responseNode != null) {
            for (JsonNode artifactNode : responseNode) {
                artifacts.add(mapper.convertValue(artifactNode, Artifact.class));
            }
        }
        return artifacts;
    }


    private Artifact getArtifactStatistics(Artifact artifact) {
        JsonNode responseNode = apiInvoker.getArtifactStatistics(artifact.getRepo(), artifact.getPath(), artifact.getName());
        if (responseNode != null) {
            artifact.setDownloadCount(responseNode.get("downloadCount").asInt());
            artifact.setLastDownloaded(new Date(responseNode.get("lastDownloaded").asLong()));
            artifact.setLastDownloaded(new Date(responseNode.get("lastDownloaded").asLong()));
            artifact.setLastDownloaded(new Date(responseNode.get("remoteDownloadCount").asLong()));
            artifact.setRemoteLastDownloaded(new Date(responseNode.get("remoteLastDownloaded").asLong()));
        }
        return artifact;
    }

    public static Comparator<Artifact> idComparator = new Comparator<Artifact>() {
        @Override
        public int compare(Artifact c1, Artifact c2) {
            if (c1.getDownloadCount() >= c2.getDownloadCount()) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    public void setApiInvoker(APIInvoker apiInvoker) {
        this.apiInvoker = apiInvoker;
    }
}


