# MicroServicios Taller
Instrucciones punto 1:
1.Correr el programa 
2. Con postman o el navegador probar los Uris:

http://localhost:8080/api/paseo(listar)
http://localhost:8080/api/paseo/actualizarPaseo/4?origen=Suiza&destino=Colombia(actualizar)
http://localhost:8080/api/paseo/createPaseo(crear). El Json para crear es:
{
  "id": 0,
  "origen": "",
  "destino": ""

}
http://localhost:8080/api/paseo/deletepaseo/4(borrar)

Instrucciones punto 2:

1. Correr el programa de eureka
2. Correr los microservicios de: Sumador, restador,divisor y multiplicador
3. Correr el programa de calculadora
4. con postman o el navegador puede proba los servicios con las siguientes uri:
SUMA: http://localhost:8888/calculadora/suma?a=5&b=50&user=vale
RESTA: http://localhost:8888/calculadora/resta?a=5&b=10&user=lau
MULTIPLICACIÓN: http://localhost:8888/calculadora/multiplicacion?a=5&b=10&user=carlos
DIVISION: http://localhost:8888/calculadora/division?a=5&b=10&user=lau
HISTORIAL:http://localhost:8888/calculadora/historial
