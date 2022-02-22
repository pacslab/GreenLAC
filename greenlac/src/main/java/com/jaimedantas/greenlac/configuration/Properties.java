package com.jaimedantas.greenlac.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "greenlac")
@Data
public class Properties {

    final Endpoint endpoint = new Endpoint();
    final Scaling scaling = new Scaling();
    final Loadbalancer loadBalancer = new Loadbalancer();

    @Data
    public static class Endpoint {
        String core;
        final Edges edges = new Edges();
    }

    @Data
    public static class Edges {
        String localEdge;
        List<String> RemoteEdges = new ArrayList<>();
    }

    @Data
    public static class Scaling {
        String policy;
        final UtilizationThreshold utilizationThreshold = new UtilizationThreshold();
    }

    @Data
    public static class UtilizationThreshold {
        String cpu;
        String memory;
    }

    @Data
    public static class Loadbalancer {
        String buffersize;
    }

}
