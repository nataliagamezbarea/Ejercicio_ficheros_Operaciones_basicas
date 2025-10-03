package utilidades;

import java.util.ArrayList;

public class Reservas {
    // guarda el numero de asiento de cada reserva
    private String numeroAsiento;
    // guarda el nombre del pasajero
    private String nombrePasajero;
    // clase del pasajero
    private String clase;
    // destino del pasajero
    private String destino;
    // contador de todas las reservas que se han creado
    private static int contador_reservas = 0;
    // lista donde se almacenan todas las reservas como objetos
    private static ArrayList<Reservas> reservas = new ArrayList<>();

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

    public static boolean getHayDestino() {
        boolean hayDestino = false;
        for (Reservas reserva : reservas) {
            if (!reserva.destino.isEmpty()) {
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
            return String.join(",", nombrePasajero,  clase , destino);
        } else {
            return String.join(",", nombrePasajero, clase);
        }
    }

    /**
     * Muestra todas las reservas en formato tabulado
     */
    public static void getMostrarReservas() {
        boolean hayDestino = Reservas.getHayDestino();

        // Imprimir encabezados
        if (hayDestino) {
            System.out.printf("%-20s%-20s%-20s%-20s%n", "NumeroAsiento", "NombrePasajero", "Clase", "Destino");
        } else {
            System.out.printf("%-20s%-20s%-20s%n", "NumeroAsiento", "NombrePasajero", "Clase");
        }

        // Imprimir cada reserva
        for (Reservas reserva : reservas) {
            if (hayDestino) {
                System.out.printf("%-20s%-20s%-20s%-20s%n", reserva.numeroAsiento, reserva.nombrePasajero, reserva.clase, reserva.destino);
            } else {
                System.out.printf("%-20s%-20s%-20s%n", reserva.numeroAsiento, reserva.nombrePasajero, reserva.clase);
            }
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