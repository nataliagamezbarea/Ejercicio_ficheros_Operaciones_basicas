package utilidades;

import java.util.ArrayList;

public class Reservas {
    // guarda el numero de asiento de cada reserva
    private String numeroAsiento;
    // guarda el nombre del pasajero
    private String nombrePasajero;
    // clase del pasajero
    private String clase;
    // contador de todas las reservas que se han creado
    private static int contador_reservas = 0;
    // lista donde se almacenan todas las reservas como objetos
    private static ArrayList<Reservas> reservas = new ArrayList<>();

    /**
     * Constructor de la clase Reservas
     * aqui se inicializan los atributos y se actualiza la lista y contador
     */
    public Reservas(String numeroAsiento, String nombrePasajero, String clase) {
        this.numeroAsiento = numeroAsiento;
        this.nombrePasajero = nombrePasajero;
        this.clase = clase;

        // incrementamos el contador de reservas
        contador_reservas++;

        // agregamos la reserva a la lista como objeto
        reservas.add(this);
    }

    // convierte la reserva en un String separado por comas
    // formato: numeroAsiento,nombrePasajero,clase
    @Override
    public String toString() {
        return numeroAsiento + "," + nombrePasajero + "," + clase;
    }

    /**
     * Muestra todas las reservas en formato tabulado
     */
    public static void getMostrarReservas() {
        System.out.printf("%-20s%-20s%-20s%n", "NumeroAsiento", "NombrePasajero", "Clase");

        for (Reservas reserva : reservas) {
            // imprimimos cada columna con ancho fijo para que quede alineado
            System.out.printf("%-20s%-20s%-20s%n", reserva.numeroAsiento, reserva.nombrePasajero, reserva.clase);
        }
    }

    /**
     * Cuenta cuántas reservas hay de una clase específica
     */
    public static int getCantidadReservasClase(String claseBuscada) {
        int contador_clase = 0;

        for (Reservas reserva : reservas) {
            // partes[2] corresponde a la columna "Clase"
            if (reserva.clase.equalsIgnoreCase(claseBuscada)) {
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
}
