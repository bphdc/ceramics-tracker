package com.hiveai;


import com.hiveai.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import util.PropertiesLoader;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Service
public class HiveAIClient implements PropertiesLoader {
    private Properties properties;
    private static final String API_URL = "https://api.thehive.ai/api/v3/black-forest-labs/flux-schnell";
    private static String API_KEY = "oCaVF6fxiMId4mM7qDKMTnZ42JMPcDRH";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public HiveAIClient() {
        loadProperties();
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public HiveAIClient(RestTemplate restTemplate) {
        loadProperties();
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Read in the props file and get/set the client id, secret, and required urls
     * for authenticating a user.
     */
    // TODO This code appears in a couple classes, consider using a startup servlet similar to adv java project
    private void loadProperties() {
        try {
            properties = loadProperties("/hiveai.properties");
            API_KEY = properties.getProperty("apikey");
        } catch (IOException ioException) {
            logger.error("Cannot load properties...{}", ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties{}", e.getMessage(), e);
        }
    }

    public Response generateImage(String prompt) throws Exception {
        // Create request body
        String requestJson = "{\n" +
                "  \"input\": {\n" +
                "    \"prompt\": \"" + prompt + "\",\n" +
                "    \"image_size\": { \"width\": 1024, \"height\": 1024 },\n" +
                "    \"num_images\": 2,\n" +
                "    \"seed\": 67,\n" +
                "    \"output_format\": \"jpeg\",\n" +
                "    \"output_quality\": 90\n" +
                "  }\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

        // Map JSON response to pojo
        Response objectResponse = objectMapper.readValue(response.getBody(), Response.class);

        return objectResponse;
    }
}

