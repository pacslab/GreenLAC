package com.jaimedantas.greenlac.loadbalancer;


import com.jaimedantas.greenlac.configuration.Properties;
import com.jaimedantas.greenlac.model.Payload;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;


@Service
public class LambdaCore {

    @Autowired
    private Properties properties;

    private final RestTemplate restTemplate;

    public LambdaCore(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public  ResponseEntity<String>  sendToLambdaCore(Payload payload) throws JSONException {

        JSONObject request = new JSONObject();
        Gson gson = new Gson();
        String json = gson.toJson(payload);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(properties.getEndpointcore(), HttpMethod.POST, entity, String.class);
        return response;

    }


}
