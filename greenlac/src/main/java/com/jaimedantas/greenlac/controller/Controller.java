package com.jaimedantas.greenlac.controller;


import com.jaimedantas.greenlac.loadbalancer.LambdaCore;
import com.jaimedantas.greenlac.model.Payload;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/greenlac/v1")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    LambdaCore lambdaCore;
    //@Client("${greenlac-endpoint-lambda-core}") @Inject RxHttpClient httpClient;
    //@Client("https://gydrsqutj5.execute-api.us-east-1.amazonaws.com/default/230mb_model_1_image") @Inject RxHttpClient httpClient;


    @RequestMapping(value = "/ml", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> proxy(@RequestBody Payload payload) throws JSONException {

        ResponseEntity<String> response = lambdaCore.sendToLambdaCore(payload);
        //lambdaCore.fetchReleases();
        String correlationId = UUID.randomUUID().toString();
        logger.info("Call to ML endpoint - "+correlationId);
        JSONObject json = new JSONObject();
        json.put("name", "student");
        //String result = httpClient.toBlocking().retrieve("/");

        return response;

    }

}
