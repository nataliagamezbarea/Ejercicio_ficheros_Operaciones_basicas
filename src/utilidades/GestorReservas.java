package utilidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestorReservas {
    // contador de todas las reservas que se han creado
    private static int contador_reservas = 0;
    // lista donde se almacenan todas las reservas como objetos
    private static final ArrayList<Reserva> listaReservas = new ArrayList<>();

    /**
     * Agrega una reserva a la lista global y aumenta el contador.
     */
    public static void agregarReserva(Reserva reserva) {
        listaReservas.add(reserva);
        contador_reservas++;
    }

    /**
     * Comprueba si al menos una reserva tiene destino asignado.
     */
    public static boolean getHayDestino() {
        for (Reserva reserva : listaReservas) {
            if (!reserva.getDestino().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra todas las reservas registradas en consola en un formato tabulado.
     */
    public static void mostrarReservas() {
        System.out.printf("%-20s%-20s%-20s%n", "NumeroAsiento", "NombrePasajero", "Clase");

        for (Reserva reserva : listaReservas) {
            System.out.printf("%-20s%-20s%-20s%n",
                    reserva.getNumeroAsiento(),
                    reserva.getNombrePasajero(),
                    reserva.getClase());
        }
    }

    /**
     * Devuelve una copia de la lista de reservas.
     */
    public static ArrayList<Reserva> getListaReservas() {
        return new ArrayList<>(listaReservas);
    }

    /**
     * Cuenta cuántas reservas hay de una clase específica.
     */
    public static int cantidadReservasClase(String claseBuscada) {
        int contadorClase = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClase().equalsIgnoreCase(claseBuscada)) {
                contadorClase++;
            }
        }
        return contadorClase;
    }

    /**
     * Devuelve el total de reservas creadas.
     */
    public static int totalReservas() {
        return contador_reservas;
    }

    /**
     * Devuelve una lista de destinos únicos de todas las reservas.
     */
    public static List<String> getDestinos() {
        Set<String> destinosUnicos = new HashSet<>();

        for (Reserva r : listaReservas) {
            String destino = r.getDestino();
            if (!destino.isEmpty()) {
                destinosUnicos.add(destino);
            }
        }

        return new ArrayList<>(destinosUnicos);
    }

    /**
     * Cuenta cuántas reservas hay para un destino específico.
     */
    public static int cantidadPorDestino(String destinoBuscado) {
        int contadorDestino = 0;
        for (Reserva r : listaReservas) {
            if (r.getDestino().equalsIgnoreCase(destinoBuscado)) {
                contadorDestino++;
            }
        }
        return contadorDestino;
    }
}
