package com.hiveai;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import persistence.HiveAIClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

public class HiveAIClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HiveAIClient hiveAIClient;
    private HiveAIClient mockHiveAIClient;

    private MockRestServiceServer mockServer;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Create the MockRestServiceServer to mock the API response
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        objectMapper = new ObjectMapper();

        hiveAIClient = new HiveAIClient();
        mockHiveAIClient = new HiveAIClient(restTemplate);
    }

//    @Test
//    @Ignore //ignore for now so i'm not constantly making api requests when i deploy my server
//    public void testGenerateImageFromRealServer() throws Exception {
//        // Make the actual API call through the HiveAIClient
//        Response response = hiveAIClient.generateImage("some hand thrown vases");
//        Logger logger = LogManager.getLogger(HiveAIClientTest.class);
//        logger.info(response.getOutput().get(0).getUrl());
//
//        // Verify the response is correctly deserialized into the POJOs
//        assertEquals("black-forest-labs/flux-schnell", response.getModel());
//        assertNotNull(response.getId());
//        assertEquals("1", response.getVersion());
//        assertEquals("some hand thrown vases", response.getInput().getPrompt());
//        assertEquals( 1024, (int) response.getInput().getImageSize().getWidth());
//        assertEquals(1024, (int) response.getInput().getImageSize().getHeight());
//        assertNotNull(response.getOutput().get(0).getUrl());
//        assertNotNull(response.getOutput().get(1).getUrl());
//
//    }

    @Test
    public void testGenerateImageFromMockServer() throws Exception {
        // Create a mock response JSON
        String mockJsonResponse = "{\n" +
                "  \"model\": \"image-gen-model\",\n" +
                "  \"id\": \"12345\",\n" +
                "  \"version\": \"v1.0\",\n" +
                "  \"input\": {\n" +
                "    \"prompt\": \"test prompt\",\n" +
                "    \"image_size\": { \"width\": 1024, \"height\": 1024 },\n" +
                "    \"num_images\": 2,\n" +
                "    \"num_inference_steps\": 15,\n" +
                "    \"seed\": 67,\n" +
                "    \"output_format\": \"jpeg\",\n" +
                "    \"output_quality\": 90\n" +
                "  },\n" +
                "  \"output\": [\n" +
                "    { \"url\": \"https://example.com/image1.jpg\" },\n" +
                "    { \"url\": \"https://example.com/image2.jpg\" }\n" +
                "  ]\n" +
                "}";

        // Mock the server to return the mock response for the given URL
        mockServer.expect(requestTo("https://api.thehive.ai/api/v3/black-forest-labs/flux-schnell"))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON));

        // Make the actual API call through the HiveAIClient
        Response response = mockHiveAIClient.generateImage("some hand thrown vases");
        Logger logger = LogManager.getLogger(HiveAIClientTest.class);
        logger.info(response.getOutput().get(0).getUrl());

        // Verify the response is correctly deserialized into the POJOs
        assertEquals("image-gen-model", response.getModel());
        assertNotNull(response.getId());
        assertEquals("v1.0", response.getVersion());
        assertEquals("test prompt", response.getInput().getPrompt());
        assertEquals( 1024, (int) response.getInput().getImageSize().getWidth());
        assertEquals(1024, (int) response.getInput().getImageSize().getHeight());
        assertEquals(67, (int) response.getInput().getSeed());
        assertEquals(15, (int) response.getInput().getNumInferenceSteps());
        assertEquals(90, (int) response.getInput().getOutputQuality());
        assertEquals("jpeg", response.getInput().getOutputFormat());
        assertEquals(2, (int) response.getInput().getNumImages());
        assertEquals("https://example.com/image1.jpg",response.getOutput().get(0).getUrl());
        assertEquals("https://example.com/image2.jpg", response.getOutput().get(1).getUrl());

    }
}

