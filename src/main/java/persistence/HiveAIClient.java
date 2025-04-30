package persistence;


import com.hiveai.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

@Service
public class HiveAIClient implements PropertiesLoader {
    private Properties properties;
    private static final String API_URL = "https://api.thehive.ai/api/v3/black-forest-labs/flux-schnell";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public HiveAIClient() {
        this(new RestTemplate());
    }

    public HiveAIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public Response generateImage(String prompt, String API_KEY) throws Exception {
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

