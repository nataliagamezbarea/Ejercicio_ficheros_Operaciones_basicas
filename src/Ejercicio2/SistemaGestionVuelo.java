package Ejercicio2;

import utilidades.GestorArchivos;
import utilidades.GestorReservas;
import utilidades.Reserva;

/**
 * Programa principal para gestionar reservas de vuelo.
 * - Crea un archivo maestro con todas las reservas.
 * - Crea instancias de la clase Reservas a partir del archivo maestro.
 * - Genera archivos separados por destino con las reservas correspondientes.
 * - Muestra un resumen de las reservas por archivo/destino.
 */
public class SistemaGestionVuelo {

    /**
     * Método principal que ejecuta la gestión de reservas.
     *
     * @param args Parámetros de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        // Nombre del archivo maestro donde se almacenan todas las reservas
        String archivoMaestro = "src/Ejercicio2/reservas_maestro.txt";

        // Crear el archivo maestro si no existe
        GestorArchivos.crearArchivo(archivoMaestro);

        // Comprobar que tenga los encabezados correctos y agregarlos si es necesario
        GestorArchivos.comprobarEncabezados(archivoMaestro, "NumeroAsiento,NombrePasajero,Clase,Destino");

        // Datos de ejemplo de las reservas
        String[] datosReservas = {
                "12A, Juan Pérez, Economy, Madrid",
                "14B, María López, Business, París",
                "21C, Carlos García, Economy, Madrid",
                "05D, Ana Sánchez, Business, Londres",
                "19E, Luis Gómez, Economy, París",
                "08F, Sofía Vargas, Economy, Londres"
        };

        // Escribir cada reserva en el archivo maestro
        for (String linea : datosReservas) {
            GestorArchivos.escribirArchivo(archivoMaestro, linea);
        }

        // Leer el archivo maestro y crear las instancias de Reservas
        GestorArchivos.leeryCrearInstanciasDesdeArchivo(archivoMaestro);

        // Mostrar total de reservas en consola
        System.out.println("Archivo: reservas_maestro.txt - Total reservas: " + GestorReservas.totalReservas());

        // Crear archivos separados para cada destino y escribir las reservas correspondientes
        for (Reserva r : GestorReservas.getListaReservas()) {
            // Nombre del archivo según destino
            String archivoPorDestino = "src/Ejercicio2/reserva_" + r.getDestino().toLowerCase() + ".txt";

            // Crear el archivo si no existe
            GestorArchivos.crearArchivo(archivoPorDestino);

            // Datos de la reserva en formato de línea
            String datos = r.getNumeroAsiento() + ", " + r.getNombrePasajero() + ", " + r.getClase() + ", " + r.getDestino();

            // Escribir la reserva en el archivo correspondiente
            GestorArchivos.escribirArchivo(archivoPorDestino, datos);
        }

        // Mostrar en consola la cantidad de reservas por cada archivo/destino
        for (String destino : GestorReservas.getDestinos()) { // getDestinos() devuelve los destinos únicos
            int cantidad = GestorReservas.cantidadPorDestino(destino);
            System.out.println("Archivo: reserva_" + destino.toLowerCase() + ".txt - Total reservas: " + cantidad);
        }
    }
}
