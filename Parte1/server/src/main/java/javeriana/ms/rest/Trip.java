package javeriana.ms.rest;


public class Trip {
    public Integer id;
    public String origen;
    public String destino;

    public Trip() {
    }

    public Trip(Integer id, String origen, String destino) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
    }

}
