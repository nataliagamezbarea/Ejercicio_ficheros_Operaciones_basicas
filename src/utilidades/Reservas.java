package utilidades;

import java.util.List;
import java.util.ArrayList;

public class Reservas {
    // guarda el numero de asiento de cada reserva
    private final String numeroAsiento;
    // guarda el nombre del pasajero
    private final String nombrePasajero;
    // clase del pasajero
    private final String clase;
    // destino del pasajero
    private final String destino;
    // contador de todas las reservas que se han creado
    private static int contador_reservas = 0;
    // lista donde se almacenan todas las reservas como objetos
    private static final ArrayList<Reservas> reservas = new ArrayList<>();

    public Reservas(String numeroAsiento, String nombrePasajero, String clase) {
        this(numeroAsiento, nombrePasajero, clase, "");
    }

    /**
     * Constructor de la clase Reservas
     * aqui se inicializan los atributos y se actualiza la lista y contador
     */
    public Reservas(String numeroAsiento, String nombrePasajero, String clase, String destino) {
        this.numeroAsiento = numeroAsiento;
        this.nombrePasajero = nombrePasajero;
        this.clase = clase;
        this.destino = destino;

        // incrementamos el contador de reservas
        contador_reservas++;

        // agregamos la reserva a la lista como objeto
        reservas.add(this);
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


    public static boolean getHayDestino() {
        boolean hayDestino = false;
        for (Reservas reserva : reservas) {
            if (!reserva.getDestino().isEmpty()) { // <--- uso getter
                hayDestino = true;
                break; // no hace falta seguir buscando
            }
        }
        return hayDestino;
    }

    // convierte la reserva en un String separado por comas
    // formato: numeroAsiento,nombrePasajero,clase , destino
    @Override
    public String toString() {
        if (Reservas.getHayDestino()) {
            return String.join(",", getNombrePasajero(), getClase(), getDestino());
        } else {
            return String.join(",", getNombrePasajero(), getClase());
        }
    }

    /**
     * Muestra todas las reservas en formato tabulado
     */
    public static void getMostrarReservas() {
        System.out.printf("%-20s%-20s%-20s%n", "NumeroAsiento", "NombrePasajero", "Clase");

        for (Reservas reserva : reservas) {
            // imprimimos cada columna con ancho fijo para que quede alineado
            System.out.printf("%-20s%-20s%-20s%n", reserva.getNumeroAsiento(), reserva.getNombrePasajero(), reserva.getClase());
        }
    }

    public static ArrayList<Reservas> getReservas() {
        return reservas;
    }

    /**
     * Cuenta cuántas reservas hay de una clase específica
     */
    public static int getCantidadReservasClase(String claseBuscada) {
        int contador_clase = 0;

        for (Reservas reserva : reservas) {
            // partes[2] corresponde a la columna "Clase"
            if (reserva.getClase().equalsIgnoreCase(claseBuscada)) {
                contador_clase++;
            }
        }

        return contador_clase;
    }

    /**
     * devuelve el total de reservas creadas
     */
    public static int getTotalReservas() {
        return contador_reservas;
    }


    public static List<String> getDestinos() {
        List<String> destinos = new ArrayList<>();
        for (Reservas r : reservas) {
            String destino = r.getDestino();
            if (!destinos.contains(destino)) { // solo añadimos si no está ya
                destinos.add(destino);
            }
        }
        return destinos;
    }

}