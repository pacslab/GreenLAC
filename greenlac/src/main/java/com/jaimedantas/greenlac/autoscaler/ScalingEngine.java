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
        switch (properties.getLoadbalancerpolicy().toLowerCase(Locale.ROOT).trim()) {
            case "core":
                uriResponse = properties.getEndpointcore();
                break;
            case "core-edge":
                if (SystemInfo.getCpuUsagePercentage() > properties.getCpuUtilizationthreshold() ||
                SystemInfo.getMemoryUsagePercentage() > properties.getMemoryutilizationthreshold()) {
                    uriResponse = properties.getEndpointcore();
                } else {
                    uriResponse = properties.getEndpointedge();
                }
                break;
            case "edge":
            default:
                uriResponse = properties.getEndpointedge();
        }
        return uriResponse;
    }

}
