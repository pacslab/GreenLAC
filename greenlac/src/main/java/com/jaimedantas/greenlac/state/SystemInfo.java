package com.jaimedantas.greenlac.state;

import lombok.SneakyThrows;

import java.util.HashMap;

public class SystemInfo {
    static double cpuUsagePercentage;
    static double memoryUsagePercentage;
    static HashMap<String,Object> incomingRequests;
    static int remoteEdgeIndex;
    static int remoteEdges;

    public SystemInfo() {
        cpuUsagePercentage = 0;
        memoryUsagePercentage = 0;
        incomingRequests = new HashMap<>();
        remoteEdgeIndex = 0;
    }

    public static void setRemoteEdges(int remoteEdges){
        SystemInfo.remoteEdges = remoteEdges;
    }

    public static int getRemoteEdgeIndex() {
        if (remoteEdgeIndex >= SystemInfo.remoteEdges){
            remoteEdgeIndex=0;
        }
        return remoteEdgeIndex++;
    }

    public static double getCpuUsagePercentage() {
        return cpuUsagePercentage;
    }

    public static void setCpuUsagePercentage(double cpuUsagePercentage) {
        SystemInfo.cpuUsagePercentage = cpuUsagePercentage;
    }

    @SneakyThrows
    public static void resetBufferIn60s(){
        Thread.sleep(60000);
        incomingRequests.clear();
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

    public static int getCurrentBufferSize() {
        return SystemInfo.incomingRequests.size();
    }
}
