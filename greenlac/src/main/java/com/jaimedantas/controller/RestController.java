package com.jaimedantas.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

import io.micronaut.validation.Validated;
import javax.inject.Inject;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Controller("/greenlac/v1")
@Validated
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    private final LambdaCore lambdaCore;

    @Inject
    public RestController(LambdaCore lambdaCore) {
        this.lambdaCore = lambdaCore;
    }
    //@Client("${greenlac-endpoint-lambda-core}") @Inject RxHttpClient httpClient;
    //@Client("https://gydrsqutj5.execute-api.us-east-1.amazonaws.com/default/230mb_model_1_image") @Inject RxHttpClient httpClient;


    /**
     * Proxy endpoint
     * @param payload
     */
    @Post(uri = "/ml")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Object> setBackendServicePolicy(Object payload) {


        //lambdaCore.fetchReleases();
        String correlationId = UUID.randomUUID().toString();
        logger.info("Call to ML endpoint - "+correlationId);
        JSONObject json = new JSONObject();
        json.put("name", "student");
        //String result = httpClient.toBlocking().retrieve("/");

        return HttpResponse.status(HttpStatus.OK).body(json);

    }

}
