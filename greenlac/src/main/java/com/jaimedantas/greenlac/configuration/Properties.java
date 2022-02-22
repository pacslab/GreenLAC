package com.jaimedantas.greenlac.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "greenlac")
@Data
public class Properties {

    String endpointedge;
    String endpointcore;
    String buffersize;
    String loadbalancerpolicy;
    String cpuUtilizationthreshold;
    String memoryutilizationthreshold;

}
