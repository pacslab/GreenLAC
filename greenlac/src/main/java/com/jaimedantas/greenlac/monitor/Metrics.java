package com.jaimedantas.greenlac.monitor;

import au.com.bytecode.opencsv.CSVWriter;
import com.jaimedantas.greenlac.state.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.sql.Timestamp;


@Service
public class Metrics {

    private static final Logger logger = LoggerFactory.getLogger(Metrics.class);


    @Scheduled(fixedDelay = 1000)
    void fetchMetrics() throws IOException {

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        double avCpuUtilization = osBean.getSystemLoadAverage()/osBean.getAvailableProcessors();
        double avCpuUtilizationPercentage = Double.parseDouble(String.format("%.4f", avCpuUtilization))*100;
        long memoryUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long memoryTotal = memoryMXBean.getHeapMemoryUsage().getMax();
        double memoryUtilizationPercentage = ((double)memoryUsed/memoryTotal)*100;
        memoryUtilizationPercentage =  Double.parseDouble(String.format("%.4f", memoryUtilizationPercentage));
        SystemInfo.setMemoryUsagePercentage(memoryUtilizationPercentage);
        SystemInfo.setCpuUsagePercentage(avCpuUtilizationPercentage);

        logger.info("CPU: "+avCpuUtilizationPercentage + " % - Memory: "+memoryUtilizationPercentage+" %");

        //write csv
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String[] entries = {
                String.valueOf(timestamp.getTime()),
                String.valueOf(SystemInfo.getCpuUsagePercentage()),
                String.valueOf(SystemInfo.getMemoryUsagePercentage()),
                String.valueOf(SystemInfo.getCurrentBufferSize())
        };

        try (CSVWriter writer = new CSVWriter(new FileWriter("history.csv", true))) {
            writer.writeNext(entries);
        }

    }

}
