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
    // lista donde se almacenan todas las reservas como Strings
    private static ArrayList<String> reservas = new ArrayList<>();

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

        // agregamos la reserva a la lista en formato String
        // esto permite recorrer y mostrar todas las reservas sin tener que manejar objetos
        reservas.add(this.toString());
    }

    // convierte la reserva en un String separado por comas
    // formato: numeroAsiento,nombrePasajero,clase
    @Override
    public String toString() {
        return numeroAsiento + "," + nombrePasajero + "," + clase;
    }

    /**
     * Muestra todas las reservas en formato tabulado
     * Separamos cada String en sus partes para poder alinear correctamente
     */
    public static void getMostrarReservas() {
        System.out.printf("%-20s%-20s%-20s%n", "NumeroAsiento", "NombrePasajero", "Clase");

        for (String reserva : reservas) {
            // separamos el String usando la coma como separador
            // partes[0] = numeroAsiento, partes[1] = nombrePasajero, partes[2] = clase
            String[] partes = reserva.split(",");

            // imprimimos cada columna con ancho fijo para que quede alineado
            System.out.printf("%-20s%-20s%-20s%n", partes[0], partes[1], partes[2]);
        }
    }

    /**
     * Cuenta cuántas reservas hay de una clase específica
     * Este método es especial porque necesitamos acceder a la columna "clase" directamente
     * Por eso se hace split de cada String
     */
    public static int getCantidadReservasClase(String claseBuscada) {
        int contador_clase = 0;

        for (String reserva : reservas) {
            // separamos la reserva en columnas usando la coma como separador
            // partes[0] = numeroAsiento, partes[1] = nombrePasajero, partes[2] = clase
            String[] partes = reserva.split(",");

            // partes[2] corresponde a la columna "Clase"
            // usamos equalsIgnoreCase para que no importe mayúsculas/minúsculas
            if (partes[2].equalsIgnoreCase(claseBuscada)) {
                contador_clase++;
            }
        }

        // devolvemos la cantidad de reservas que coinciden con la clase buscada
        return contador_clase;
    }

    /**
     * devuelve el total de reservas creadas
     */
    public static int getTotalReservas() {
        return contador_reservas;
    }
}
