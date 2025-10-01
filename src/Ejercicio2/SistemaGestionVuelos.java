package Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import GestorArchivos.GestorArchivos;

/**
 * Programa para gestionar reservas de vuelos.
 * <p>
 * Esta clase hace lo siguiente:
 * - Define un conjunto de reservas de ejemplo.
 * - Crea un archivo maestro con todas las reservas.
 * - Clasifica las reservas por destino, creando un archivo por cada destino.
 * - Muestra un resumen con la cantidad de reservas por destino.
 */
public class SistemaGestionVuelos {

    /**
     * Método principal que arranca el programa.
     * Crea reservas de ejemplo, genera el archivo maestro y llama
     * al método que clasifica las reservas por destino.
     *
     * @param args Argumentos de la línea de comandos (no se usan).
     */
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
     * Clasifica las reservas por destino.
     * <p>
     * Por cada reserva del archivo maestro:
     * - Se crea un archivo por destino (si no existe se crea).
     * - Se guarda la reserva en el archivo correspondiente.
     * - Se lleva un contador de cuántas reservas hay por destino.
     * <p>
     * Al final, se imprime un resumen con los archivos creados y el número de reservas por cada destino.
     *
     * @param archivoMaestro Nombre del archivo maestro que contiene todas las reservas.
     */
    public static void clasificarYContarPorDestino(String archivoMaestro) {
        List<String> lineas = GestorArchivos.leerArchivoLineas(archivoMaestro);

        // Lista para registrar los destinos que ya se han procesado
        List<String> destinosProcesados = new ArrayList<>();
        List<Integer> cantidadPorDestino = new ArrayList<>();

        for (String linea : lineas) {
            String[] datosLinea = linea.split(", ");
            if (datosLinea.length < 4) {
                // Guardar error si falta algún dato
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
