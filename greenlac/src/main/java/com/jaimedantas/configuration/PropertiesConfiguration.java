package com.jaimedantas.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties("greenlac")
public class PropertiesConfiguration {

    String endpointLambdaEdge;
    String endpointLambdaCore;
    String bufferSize;
    String loadBalancerPolicy;
    String cpuUtilizationThreshold;
    String memoryUtilizationThreshold;

}
