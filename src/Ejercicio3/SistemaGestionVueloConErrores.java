package Ejercicio3;

import utilidades.GestorArchivos;
import utilidades.GestorReservas;

/**
 * Programa principal para gestionar reservas de vuelo con posibles errores.
 * - Crea un archivo maestro con reservas (algunas pueden estar incompletas o incorrectas).
 * - Valida las reservas y separa las válidas de las inválidas.
 * - Las reservas válidas se guardan en archivos por destino.
 * - Las reservas con errores se registran en un log de errores.
 */
public class SistemaGestionVueloConErrores {

    /**
     * Método principal que ejecuta la gestión de reservas con errores.
     * @param args Parámetros de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        // Archivo maestro con posibles errores
        String archivoMaestro = "src/Ejercicio3/reservas_maestro_con_errores.txt";

        // Crear el archivo si no existe
        GestorArchivos.crearArchivo(archivoMaestro);

        // Comprobar encabezados y añadirlos si el archivo está vacío
        GestorArchivos.comprobarEncabezados(archivoMaestro, "NumeroAsiento,NombrePasajero,Clase,Destino");

        // Datos de ejemplo (algunas líneas están incompletas a propósito)
        String[] datosReservas = {
                "12A, Juan Pérez, Economy, Madrid",         // correcta
                "14B, María López, Business",             // falta destino
                "21C, Carlos García, Economy, Madrid",    // correcta
                "05D, , Business, Londres",               // falta nombre
                "19E, Luis Gómez, Economy, París",        // correcta
                "08F, Sofía Vargas, Economy, " ,          // falta destino
                " , Ana Sánchez, Business, Londres",      // falta número asiento
                "10G, Jorge,Economy,Lisboa",              // correcta, sin espacios extra
                "11H, , , "                               // completamente vacía excepto comas
        };

        // Escribir los datos iniciales en el archivo maestro
        for (String linea : datosReservas) {
            GestorArchivos.escribirArchivo(archivoMaestro, linea);
        }

        // Procesar el archivo maestro: crear reservas válidas y registrar errores
        GestorArchivos.procesarReservasConErrores(archivoMaestro);

        // Mostrar un resumen de las reservas válidas por destino
        for (String destino : GestorReservas.getDestinos()) {
            int cantidad = GestorReservas.cantidadPorDestino(destino);
            System.out.println("Archivo: reserva_" + destino.toLowerCase() + ".txt - Total reservas: " + cantidad);
        }

        // Mostrar el contenido del registro de errores en consola
        System.out.println("\nContenido del registro de errores:");
        GestorArchivos.mostrarRegistroErrores("src/Ejercicio3/registro_errores.log");
    }
}
