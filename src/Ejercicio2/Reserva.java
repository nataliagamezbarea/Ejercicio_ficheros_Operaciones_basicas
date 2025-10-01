package Ejercicio2;

/**
 * Clase que representa una reserva de vuelo.
 * Cada objeto de esta clase guarda la información de un pasajero y su asiento.
 */
public class Reserva {

    /** Número de asiento del pasajero (ej: "12A") */
    private final String asiento;

    /** Nombre del pasajero */
    private final String nombre;

    /** Clase del vuelo (ej: "Economy" o "Business") */
    private final String claseVuelo;

    /** Destino del vuelo (ej: "Madrid") */
    private final String destino;

    /**
     * Constructor de la clase Reserva.
     * Crea un objeto Reserva con todos sus datos.
     *
     * @param asiento Número de asiento del pasajero
     * @param nombre Nombre del pasajero
     * @param claseVuelo Clase del vuelo (ej: "Economy" o "Business")
     * @param destino Destino del vuelo (ej: "Madrid")
     */
    public Reserva(String asiento, String nombre, String claseVuelo, String destino) {
        this.asiento = asiento;
        this.nombre = nombre;
        this.claseVuelo = claseVuelo;
        this.destino = destino;
    }

    /**
     * Devuelve el destino de la reserva.
     *
     * @return Destino del vuelo
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Devuelve una representación en texto del objeto Reserva.
     * Convierte los atributos del objeto en una cadena separada por comas.
     *
     * @return Cadena con los datos de la reserva (asiento, nombre, clase de vuelo, destino)
     */
    @Override
    public String toString() {
        return String.join(", ", asiento, nombre, claseVuelo, destino);
    }
}
