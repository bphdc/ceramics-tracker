package persistence;


import com.fasterxml.jackson.databind.JsonNode;
import com.hiveai.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

/**
 * hive ai class
 */
@Service
public class HiveAIClient {
    private Properties properties;
    private static final String API_URL = "https://api.thehive.ai/api/v3/black-forest-labs/flux-schnell";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * construct a hive ai instance
     */
    public HiveAIClient() {
        this(new RestTemplate());
    }

    /**
     * construct a hive ai instance
     * @param restTemplate the restTemplate
     */
    public HiveAIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * generate a new image
     * @param prompt user prompt
     * @param API_KEY the api key
     * @return
     * @throws Exception
     */
    public Response generateImage(String prompt, String API_KEY) throws Exception {
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

        try {
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            if (jsonNode.has("status_code")) {
                int statusCode = jsonNode.get("status_code").asInt();
                String message = jsonNode.has("message") ? jsonNode.get("message").asText() : "Unknown error";

                switch (statusCode) {
                    case 451:
                        logger.warn("Moderation filter triggered: {}", message);
                        throw new Exception("Image failed moderation: " + message);
                    case 429:
                        logger.error("Rate limit exceeded: {}", message);
                        throw new Exception("Too many requests: " + message);
                    case 405:
                        logger.error("Organization account issue: {}", message);
                        throw new Exception("Organization is paused or out of credits: " + message);
                    default:
                        logger.error("Unhandled API error: {} - {}", statusCode, message);
                        throw new Exception("Unhandled API error: " + message);
                }
            }

            return objectMapper.treeToValue(jsonNode, Response.class);

        } catch (HttpClientErrorException e) {
            logger.error("HTTP Error: {}", e.getMessage(), e);
            throw new Exception("HTTP error from Hive AI API: " + e.getStatusCode());
        } catch (Exception e) {
            logger.error("Unexpected error during image generation", e);
            throw e;
        }
    }
}

