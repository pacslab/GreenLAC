package com.jaimedantas.greenlac.state;

import java.util.HashMap;

public class SystemInfo {
    static double cpuUsagePercentage;
    static double memoryUsagePercentage;
    static HashMap<String,Object> incomingRequests;

    public SystemInfo() {
        cpuUsagePercentage = 0;
        memoryUsagePercentage = 0;
        incomingRequests = new HashMap<>();
    }

    public static double getCpuUsagePercentage() {
        return cpuUsagePercentage;
    }

    public static void setCpuUsagePercentage(double cpuUsagePercentage) {
        SystemInfo.cpuUsagePercentage = cpuUsagePercentage;
    }

    public static double getMemoryUsagePercentage() {
        return memoryUsagePercentage;
    }

    public static void setMemoryUsagePercentage(double memoryUsagePercentage) {
        SystemInfo.memoryUsagePercentage = memoryUsagePercentage;
    }

    public static void addRequest(String correlationId, Object request) {
        SystemInfo.incomingRequests.put(correlationId, request);
    }

    public static void removeRequest(String correlationId) {
        SystemInfo.incomingRequests.remove(correlationId);
    }

    public int getCurrentBufferSize() {
        return SystemInfo.incomingRequests.size();
    }
}
