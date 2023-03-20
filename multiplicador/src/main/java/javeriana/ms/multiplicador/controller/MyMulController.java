package javeriana.ms.multiplicador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyMulController {
    @Autowired
    Environment environment;

    @GetMapping("/multiplicacion")
    public String sum(@RequestParam int a,@RequestParam int b ){
        String port = environment.getProperty("local.server.port");
        float result = a*b;
        String response = "Resultado: " + result + "-> Microservicio multiplicacion corriendo por el puerto: " + port;
        return response;
    }
}
