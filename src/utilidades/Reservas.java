package utilidades;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Reservas {
    // guarda el número de asiento de cada reserva
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
    private static final ArrayList<Reservas> listaReservas = new ArrayList<>();

    /**
     * Constructor simplificado de la clase Reservas.
     * Crea una reserva sin especificar destino.
     *
     * @param numeroAsiento El número de asiento del pasajero (ej. "12A")
     * @param nombrePasajero El nombre completo del pasajero
     * @param clase La clase del pasajero (ej. "Economy", "Business")
     */
    public Reservas(String numeroAsiento, String nombrePasajero, String clase) {
        this(numeroAsiento, nombrePasajero, clase, "");
    }

    /**
     * Constructor completo de la clase Reservas.
     * Inicializa todos los atributos y añade la reserva a la lista global.
     *
     * @param numeroAsiento El número de asiento del pasajero
     * @param nombrePasajero El nombre completo del pasajero
     * @param clase La clase del pasajero
     * @param destino El destino del pasajero (ej. "Madrid")
     */
    public Reservas(String numeroAsiento, String nombrePasajero, String clase, String destino) {
        this.numeroAsiento = numeroAsiento;
        this.nombrePasajero = nombrePasajero;
        this.clase = clase;
        this.destino = destino;

        // Incrementamos el contador de reservas
        contador_reservas++;

        // Agregamos la reserva a la lista global
        listaReservas.add(this);
    }

    /**
     * Devuelve el número de asiento de la reserva.
     *
     * @return El número de asiento como String
     */
    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    /**
     * Devuelve el nombre del pasajero de la reserva.
     *
     * @return El nombre del pasajero
     */
    public String getNombrePasajero() {
        return nombrePasajero;
    }

    /**
     * Devuelve la clase del pasajero (Economy, Business, etc.).
     *
     * @return La clase de la reserva
     */
    public String getClase() {
        return clase;
    }

    /**
     * Devuelve el destino de la reserva.
     *
     * @return El destino como String
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Comprueba si al menos una reserva tiene destino asignado.
     *
     * @return true si hay alguna reserva con destino, false si no
     */
    public static boolean getHayDestino() {
        boolean hayDestino = false;
        for (Reservas reserva : listaReservas) {
            if (!reserva.getDestino().isEmpty()) {
                hayDestino = true;
                break;
            }
        }
        return hayDestino;
    }

    /**
     * Convierte la reserva en un String separado por comas.
     * Si hay destinos, se incluyen; si no, solo asiento, nombre y clase.
     *
     * @return La reserva en formato String
     */
    @Override
    public String toString() {
        if (Reservas.getHayDestino()) {
            return String.join(",", getNumeroAsiento(), getNombrePasajero(), getClase(), getDestino());
        } else {
            return String.join(",", getNumeroAsiento(), getNombrePasajero(), getClase());
        }
    }

    /**
     * Muestra todas las reservas registradas en consola en un formato tabulado.
     * Cada columna tiene ancho fijo para que sea legible.
     */
    public static void getMostrarReservas() {
        System.out.printf("%-20s%-20s%-20s%n", "NumeroAsiento", "NombrePasajero", "Clase");

        for (Reservas reserva : listaReservas) {
            System.out.printf("%-20s%-20s%-20s%n", reserva.getNumeroAsiento(), reserva.getNombrePasajero(), reserva.getClase());
        }
    }

    /**
     * Devuelve una copia de la lista de reservas.
     * Esto evita que se pueda modificar la lista original desde fuera de la clase.
     *
     * @return Una lista de objetos Reservas
     */
    public static ArrayList<Reservas> getListaReservas() {
        return new ArrayList<>(listaReservas);
    }

    /**
     * Cuenta cuántas reservas hay de una clase específica.
     *
     * @param claseBuscada La clase que quieres contar (ej. "Business")
     * @return La cantidad de reservas de esa clase
     */
    public static int getCantidadReservasClase(String claseBuscada) {
        int contadorClase = 0;

        for (Reservas reserva : listaReservas) {
            if (reserva.getClase().equalsIgnoreCase(claseBuscada)) {
                contadorClase++;
            }
        }
        return contadorClase;
    }

    /**
     * Devuelve el total de reservas creadas.
     *
     * @return Número total de reservas
     */
    public static int getTotalReservas() {
        return contador_reservas;
    }

    /**
     * Devuelve una lista de destinos únicos de todas las reservas.
     *
     * @return Lista de destinos sin duplicados
     */
    public static List<String> getDestinos() {
        Set<String> destinosUnicos = new HashSet<>();

        for (Reservas r : listaReservas) {
            String destino = r.getDestino();
            if (!destino.isEmpty()) {
                destinosUnicos.add(destino);
            }
        }

        return new ArrayList<>(destinosUnicos);
    }

    /**
     * Cuenta cuántas reservas hay para un destino específico.
     *
     * @param destinoBuscado El destino que quieres contar (ej. "Madrid")
     * @return Número de reservas para ese destino
     */
    public static int getCantidadPorDestino(String destinoBuscado) {
        int contadorDestino = 0;
        for (Reservas r : listaReservas) {
            if (r.getDestino().equalsIgnoreCase(destinoBuscado)) {
                contadorDestino++;
            }
        }
        return contadorDestino;
    }
}
