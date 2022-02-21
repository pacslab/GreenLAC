package com.jaimedantas;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
    info = @Info(
            title = "greenlac",
            description = "GreenLac",
            version = "0.1",
            license = @License(name = "MIT License", url = "https://github.com/pacslab/GreenLAC/blob/main/LICENSE"),
            contact = @Contact(url = "https://jaimedantas.com", name = "Jaime", email = "jaimejales@hotmail.com")
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
