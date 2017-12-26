package com.ssk.challenge.jgrog;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


public class APIInvoker {

    public static String ARTIFACTORY_BASE_URL = "http://35.224.254.203:8083/artifactory";
    public static String ACCESS_TOKEN_URL = ARTIFACTORY_BASE_URL + "/api/security/token";
    public static String REPO_SEARCH_URL = ARTIFACTORY_BASE_URL + "/api/search/aql";
    public static String USERNAME = "admin";
    public static String PASSWORD = "tuG28kCMie";
    public static String BASIC_AUTH = "Basic YWRtaW46dHVHMjhrQ01pZQ==";


    ObjectMapper mapper = new ObjectMapper();

    private String ACCESS_TOKEN = null;

    public JsonNode getArtifactStatistics(String repo, String path, String name) {
        Client client = ClientBuilder.newClient();
        String url = ARTIFACTORY_BASE_URL;
        if (!".".equals(path)) {
            url = String.format(ARTIFACTORY_BASE_URL + "/api/storage/%s/%s/%s?stats", repo, path, name);
        } else {
            url = String.format(ARTIFACTORY_BASE_URL + "/api/storage/%s/%s?stats", repo, name);
        }

        Response res = client.target(url).request(MediaType.APPLICATION_JSON)
                .header("authorization", generateAccessToken())
                .get();
        JsonNode responseNode = null;
        try {
            return mapper.readTree(res.readEntity(String.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public JsonNode getArtifacts(String repoName) {

        try {
            Client client = ClientBuilder.newClient();
            Response res = client.target(REPO_SEARCH_URL).request(MediaType.TEXT_PLAIN)
                    .header("authorization", generateAccessToken())
                    .post(Entity.entity("items.find({\"repo\":{\"$eq\":\"" + repoName + "\"}})", MediaType.TEXT_PLAIN));
            JsonNode responseNode = mapper.readTree(res.readEntity(String.class));
            return responseNode.get("results");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateAccessToken() {
        if (ACCESS_TOKEN == null) {
            Client client = ClientBuilder.newClient();
            Form form = new Form();
            form.param("username", USERNAME);
            form.param("scope", "member-of-groups:readers");
            Response response = client.target(ACCESS_TOKEN_URL).request(MediaType.APPLICATION_FORM_URLENCODED)
                    .header("authorization", BASIC_AUTH)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
            try {
                JsonNode jsonNode = mapper.readTree(response.readEntity(String.class));
                ACCESS_TOKEN = jsonNode.get("access_token").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Bearer " + ACCESS_TOKEN;
    }
}