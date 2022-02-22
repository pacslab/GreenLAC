package com.jaimedantas.controller;


import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

@Singleton
public class LambdaCore {

    @Inject
    @Client("https://gydrsqutj5.execute-api.us-east-1.amazonaws.com/default/230mb_model_1_image")
    private final RxHttpClient httpClient;

    @Inject
    public LambdaCore(RxHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    Maybe<String> fetchReleases() {
        HttpRequest<?> req = HttpRequest.GET("/")
                .header(USER_AGENT, "Micronaut HTTP Client")
                .header(ACCEPT, "application/json");
        Flowable<String> flowable = httpClient.retrieve(req);
        return flowable.firstElement();
    }

    public Object execute(){
        JSONObject json = new JSONObject();
        json.put("name", "student");
        HttpResponse<String> auditResponse = this.httpClient.toBlocking()
                .exchange(HttpRequest.POST("https://gydrsqutj5.execute-api.us-east-1.amazonaws.com/default/230mb_model_1_image", json));
        return auditResponse;
    }


}
