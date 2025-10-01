package Ejercicio3;

import java.util.List;
import java.util.ArrayList;
import GestorArchivos.GestorArchivos;

/**
 * Programa para gestionar reservas de vuelos (Parte 3).
 * <p>
 * Esta versión incluye:
 * - Archivo maestro con datos válidos y algunos con errores.
 * - Clasificación por destino.
 * - Registro automático de errores utilizando GestorArchivos.
 */
public class SistemaGestionVuelosConErrores {

    public static void main(String[] args) {

        // Nombre del archivo maestro con posibles errores
        String archivoMaestro = "reservas_maestro_con_errores.txt";

        // Crear el archivo si no existe
        GestorArchivos.crearArchivoSiNoExiste(archivoMaestro);

        // Datos iniciales (válidos e inválidos)
        List<String> reservasIniciales = List.of(
                "12A, Juan Pérez, Economy, Madrid",
                "14B, María López, Business",                // ERROR: falta destino
                "",                                          // ERROR: línea vacía
                "21C, Carlos García, Economy, Madrid",
                "05D, Ana Sánchez, Business, Londres",
                "19E, Luis Gómez",                           // ERROR: falta clase y destino
                "08F, Sofía Vargas, Economy, Londres",
                "07G, Pedro Martínez, Economy, París",
                "09H, Laura Torres, Business"                // ERROR: falta destino
        );

        // Escribir los datos en el archivo maestro
        for (String linea : reservasIniciales) {
            GestorArchivos.appendArchivo(archivoMaestro, linea);
        }

        // Procesar reservas: clasificar válidas y registrar errores
        procesarReservas(archivoMaestro);
    }

    /**
     * Procesa las reservas del archivo maestro:
     * - Guarda las válidas en el archivo de destino correspondiente.
     * - Registra las inválidas en registro_errores.log usando GestorArchivos.
     */
    public static void procesarReservas(String archivoMaestro) {
        List<String> lineas = GestorArchivos.leerArchivoLineas(archivoMaestro);

        List<String> destinosProcesados = new ArrayList<>();
        List<Integer> cantidadPorDestino = new ArrayList<>();

        for (String linea : lineas) {
            String[] datosLinea = linea.split(", ");

            if (datosLinea.length < 4) {
                // Usar el método de GestorArchivos para registrar el error
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

        // Mostrar errores registrados
        mostrarErrores();
    }

    /**
     * Muestra el contenido del archivo registro_errores.log en consola.
     */
    private static void mostrarErrores() {
        List<String> errores = GestorArchivos.leerArchivoLineas("registro_errores.log");
        if (!errores.isEmpty()) {
            System.out.println("\nRegistro de errores:");
            for (String e : errores) {
                System.out.println(e);
            }
        }
    }
}
