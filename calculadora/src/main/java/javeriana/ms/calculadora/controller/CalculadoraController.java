package javeriana.ms.calculadora.controller;

import javeriana.ms.calculadora.models.Historial;
import javeriana.ms.calculadora.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javeriana.ms.calculadora.FileHelper;

@RestController
public class CalculadoraController {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Autowired
    HistorialRepository repositorio;
    @GetMapping("/calculadora/suma")
    public String calculadoraSuma(@RequestParam int a, @RequestParam int b, @RequestParam String user) throws IOException {
        System.out.println("llegueee");
        String response = restTemplate.getForObject("http://SUMADOR/suma?a={a}&b={b}", String.class, a, b);
        registrarOperacion(a,b,user,"suma");
        return response;
    }

    @GetMapping("/calculadora/resta")
    public String calculadoraResta(@RequestParam int a, @RequestParam int b, @RequestParam String user) throws IOException {
        String response = restTemplate.getForObject("http://RESTADOR/resta?a={a}&b={b}", String.class, a, b);
        registrarOperacion(a,b,user,"resta");
        return response;
    }

    @GetMapping("/calculadora/multiplicacion")
    public String calculadoraMultiplicacion(@RequestParam int a, @RequestParam int b, @RequestParam String user) throws IOException {
        String response = restTemplate.getForObject("http://multiplicador/multiplicacion?a={a}&b={b}", String.class, a, b);
        registrarOperacion(a,b,user,"multiplicacion");
        return response;
    }

    @GetMapping("/calculadora/division")
    public String calculadoraDivision(@RequestParam int a, @RequestParam int b, @RequestParam String user) throws IOException {
        String response = restTemplate.getForObject("http://divisor/division?a={a}&b={b}", String.class, a, b);
        registrarOperacion(a,b,user,"division");
        return response;
    }

    public void registrarOperacion( int a,  int b,  String user, String operacion){
        Historial historial = new Historial();
        historial.operacion = operacion;
        historial.operador1 = a;
        historial.operador2 = b;
        historial.resultado = a+b;
        historial.usuario = user;
        historial.fecha = new Date(System.currentTimeMillis());
        repositorio.save(historial);
    }

    @GetMapping("/calculadora/historial")
    public @ResponseBody Iterable<Historial> calculadoraHistorial(){
        return (repositorio.findAll());
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}