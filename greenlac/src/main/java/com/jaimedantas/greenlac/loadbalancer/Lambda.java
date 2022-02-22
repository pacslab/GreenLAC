package com.jaimedantas.greenlac.loadbalancer;


import com.jaimedantas.greenlac.autoscaler.ScalingEngine;
import com.jaimedantas.greenlac.configuration.Properties;
import com.jaimedantas.greenlac.model.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

import java.util.Objects;


@Service
public class Lambda {

    private final RestTemplate restTemplate;

    @Autowired
    Properties properties;

    private static final Logger logger = LoggerFactory.getLogger(ScalingEngine.class);


    public Lambda(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public ResponseEntity<String> sendToLambda(Payload payload, String uri) {

        Gson gson = new Gson();
        String json = gson.toJson(payload);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        logger.info("Sending request to: " + uri);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate
                    .exchange(uri, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (Objects.isNull(response) || !response.getStatusCode().is2xxSuccessful()) {
            logger.warn("Error in request, sending it to core. ");
            response = restTemplate
                    .exchange(properties.getEndpoint().getCore(), HttpMethod.POST, entity, String.class);
        }
        return response;

    }


}
