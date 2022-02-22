package com.jaimedantas.greenlac.autoscaler;

import com.jaimedantas.greenlac.configuration.Properties;
import com.jaimedantas.greenlac.state.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ScalingEngine {

    @Autowired
    Properties properties;

    public String requestRoute(){

        String uriResponse = null;
        boolean shouldScaler = SystemInfo.getCpuUsagePercentage() > Integer.parseInt(properties.getScaling().getUtilizationThreshold().getCpu()) ||
                SystemInfo.getMemoryUsagePercentage() > Integer.parseInt(properties.getScaling().getUtilizationThreshold().getMemory());

        switch (properties.getScaling().getPolicy().toLowerCase(Locale.ROOT).trim()) {
            case "core":
                uriResponse = properties.getEndpoint().getCore();
                break;
            case "core-edge-pc":
                if (shouldScaler) {
                    uriResponse = properties.getEndpoint().getCore();
                } else {
                    uriResponse = properties.getEndpoint().getEdges().getLocalEdge();
                }
                break;
            case "core-edge-pe":
                if (shouldScaler) {
                    uriResponse = properties.getEndpoint().getEdges().getRemoteEdges().get(SystemInfo.getRemoteEdgeIndex());
                } else {
                    uriResponse = properties.getEndpoint().getEdges().getLocalEdge();
                }
                break;
            case "edge":
            default:
                uriResponse = properties.getEndpoint().getEdges().getLocalEdge();
        }
        return uriResponse;
    }

}
