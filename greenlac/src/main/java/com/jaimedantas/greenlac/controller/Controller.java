package com.jaimedantas.greenlac.controller;


import com.jaimedantas.greenlac.autoscaler.ScalingEngine;
import com.jaimedantas.greenlac.configuration.Properties;
import com.jaimedantas.greenlac.loadbalancer.Lambda;
import com.jaimedantas.greenlac.model.Payload;
import com.jaimedantas.greenlac.state.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/greenlac/v1")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    Lambda lambda;

    @Autowired
    ScalingEngine scalingEngine;

    @Autowired
    Properties properties;

    @RequestMapping(value = "/ml", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> proxy(@RequestBody Payload payload) {
        ResponseEntity<String> response;
        if (SystemInfo.getCurrentBufferSize() <= Integer.parseInt(properties.getLoadBalancer().getBuffersize())) {
            String correlationId = UUID.randomUUID().toString();
            SystemInfo.addRequest(correlationId, payload);
            logger.info("Request to ml endpoint - " + correlationId);
            response = lambda.sendToLambda(payload, scalingEngine.requestRoute());
            SystemInfo.removeRequest(correlationId);
            logger.info("Response to ml endpoint - " + correlationId);

        } else {
            logger.warn("Request rejected - buffer full");
            response = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
            SystemInfo.resetBufferIn60s();

        }
        return response;

    }

}
