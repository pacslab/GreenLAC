package com.jaimedantas.monitor;

import com.jaimedantas.state.SystemInfo;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;



@Singleton
public class Metrics {

    private static final Logger logger = LoggerFactory.getLogger(Metrics.class);


    @Scheduled(fixedDelay = "1s", initialDelay = "1s")
    void fetchMetrics() {

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        double avCpuUtilization = osBean.getSystemLoadAverage()/osBean.getAvailableProcessors();
        double avCpuUtilizationPercentage = Double.parseDouble(String.format("%.3f", avCpuUtilization))*100;
        long memoryUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long memoryTotal = memoryMXBean.getHeapMemoryUsage().getMax();
        double memoryUtilizationPercentage = ((double)memoryUsed/memoryTotal)*100;

        SystemInfo.setMemoryUsagePercentage(memoryUtilizationPercentage);
        SystemInfo.setCpuUsagePercentage(memoryUtilizationPercentage);

        logger.info("CPU: "+avCpuUtilizationPercentage + " % - Memory: "+memoryUtilizationPercentage+" %");

    }

}
