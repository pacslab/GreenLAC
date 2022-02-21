package com.jaimedantas.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Controller("/greenlac/v1/")
@Validated
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    /**
     * Proxy endpoint
     * @param payload
     */
    @Post(uri = "/ml")
    public void setBackendServicePolicy(Object payload) {

        String correlationId = UUID.randomUUID().toString();
        logger.info("Call to ML endpoint - "+correlationId);

    }

}
