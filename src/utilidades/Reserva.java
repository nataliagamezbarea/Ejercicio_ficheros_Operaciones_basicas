package utilidades;

public class Reserva {
    // guarda el número de asiento de cada reserva
    private final String numeroAsiento;
    // guarda el nombre del pasajero
    private final String nombrePasajero;
    // clase del pasajero
    private final String clase;
    // destino del pasajero
    private final String destino;

    /**
     * Constructor simplificado de la clase Reserva.
     * Crea una reserva sin especificar destino.
     */
    public Reserva(String numeroAsiento, String nombrePasajero, String clase) {
        this(numeroAsiento, nombrePasajero, clase, "");
    }

    /**
     * Constructor completo de la clase Reserva.
     */
    public Reserva(String numeroAsiento, String nombrePasajero, String clase, String destino) {
        this.numeroAsiento = numeroAsiento;
        this.nombrePasajero = nombrePasajero;
        this.clase = clase;
        this.destino = destino;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public String getClase() {
        return clase;
    }

    public String getDestino() {
        return destino;
    }

    /**
     * Convierte la reserva en un String separado por comas.
     */
    @Override
    public String toString() {
        if (!destino.isEmpty()) {
            return String.join(",", numeroAsiento, nombrePasajero, clase, destino);
        } else {
            return String.join(",", numeroAsiento, nombrePasajero, clase);
        }
    }
}
