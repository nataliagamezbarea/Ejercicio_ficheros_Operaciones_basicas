package Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import sistemaGestionVuelos.GestorArchivos;

/**
 * Clase principal que ejecuta el programa de gestión de reservas de vuelos.
 * Crea un archivo maestro y clasifica las reservas por destino usando GestorArchivos.
 */
public class SistemaGestionVuelos {

    public static void main(String[] args) {

        // Datos de ejemplo: asiento, nombre, clase y destino
        String[][] datos = {
                {"12A", "Juan Pérez", "Economy", "Madrid"},
                {"14B", "María López", "Business", "París"},
                {"21C", "Carlos García", "Economy", "Madrid"},
                {"05D", "Ana Sánchez", "Business", "Londres"},
                {"19E", "Luis Gómez", "Economy", "París"},
                {"08F", "Sofía Vargas", "Economy", "Londres"}
        };

        // Crear lista de objetos Reserva
        List<Reserva> reservas = new ArrayList<>();
        for (String[] r : datos) {
            reservas.add(new Reserva(r[0], r[1], r[2], r[3]));
        }

        // Nombre del archivo maestro
        String archivoMaestro = "reservas_maestro.txt";

        // Crear el archivo maestro si no existe
        GestorArchivos.crearArchivoSiNoExiste(archivoMaestro);

        // Escribir todas las reservas en el archivo maestro
        for (Reserva r : reservas) {
            GestorArchivos.appendArchivo(archivoMaestro, r.toString());
        }
        System.out.println("\nArchivo maestro '" + archivoMaestro + "' creado con éxito.\n");

        // Clasificar reservas por destino y mostrar resumen
        clasificarYContarPorDestino(archivoMaestro);
    }

    /**
     * Clasifica las reservas por destino, crea archivos por destino y muestra un resumen.
     *
     * @param archivoMaestro Nombre del archivo maestro que contiene todas las reservas.
     */
    public static void clasificarYContarPorDestino(String archivoMaestro) {
        List<String> lineas = GestorArchivos.leerArchivoLineas(archivoMaestro);
        List<String> destinosProcesados = new ArrayList<>();
        List<Integer> cantidadPorDestino = new ArrayList<>();

        for (String linea : lineas) {
            String[] datosLinea = linea.split(", ");
            if (datosLinea.length < 4) {
                GestorArchivos.registrarError(linea, "Falta algún campo en la reserva");
                continue;
            }

            String destino = datosLinea[3];
            String archivoDestino = "reservas_" + destino.toLowerCase() + ".txt";
            GestorArchivos.appendArchivo(archivoDestino, linea);

            // Contar reservas por destino
            int index = destinosProcesados.indexOf(destino);
            if (index == -1) {
                destinosProcesados.add(destino);
                cantidadPorDestino.add(1);
            } else {
                cantidadPorDestino.set(index, cantidadPorDestino.get(index) + 1);
            }
        }

        // Mostrar resumen por destino
        System.out.println("\nResumen de archivos por destino:");
        for (int i = 0; i < destinosProcesados.size(); i++) {
            String archivoDestino = "reservas_" + destinosProcesados.get(i).toLowerCase() + ".txt";
            System.out.println(archivoDestino + " -> " + cantidadPorDestino.get(i) + " reservas");
        }
    }
}
