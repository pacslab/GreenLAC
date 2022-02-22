package com.jaimedantas;

import com.jaimedantas.state.SystemInfo;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        Micronaut.run(Application.class, args);
    }
}
