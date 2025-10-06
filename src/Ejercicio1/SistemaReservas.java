package Ejercicio1;

import utilidades.GestorArchivos;
import utilidades.GestorReservas;

/**
 * Programa principal para crear y gestionar reservas básicas.
 * - Crea un archivo de reservas.
 * - Escribe algunas reservas de ejemplo.
 * - Crea instancias de la clase Reserva a partir del archivo.
 * - Muestra todas las reservas en consola y el total.
 * - Calcula el total de reservas de la clase Business.
 */
public class SistemaReservas {

    /**
     * Método principal que ejecuta el flujo de reservas.
     *
     * @param args Parámetros de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        // Nombre del archivo donde se almacenarán las reservas
        String nombre_archivo = "src/Ejercicio1/reservas.txt";

        // Crear el archivo si no existe
        GestorArchivos.crearArchivo(nombre_archivo);

        // Comprobar que el archivo tenga los encabezados correctos
        GestorArchivos.comprobarEncabezados(nombre_archivo, "NumeroAsiento,NombrePasajero,Clase");

        // Lista de datos de ejemplo a escribir en el archivo
        String[] datosEscribir = {
                "12A,Juan Pérez,Economy",
                "14B,María López,Business",
                "21C,Carlos García,Economy"
        };

        // Escribir cada reserva en el archivo
        for (String linia : datosEscribir) {
            GestorArchivos.escribirArchivo(nombre_archivo, linia);
        }

        // Crear instancias de la clase Reserva a partir del archivo
        // Esta función ahora debe agregar automáticamente cada reserva al GestorReservas
        GestorArchivos.leeryCrearInstanciasDesdeArchivo(nombre_archivo);

        // Mostrar todas las reservas en consola
        GestorReservas.mostrarReservas();

        // Mostrar el número total de reservas
        System.out.println("El número total de reservas es de: " + GestorReservas.totalReservas());

        // Mostrar el total de reservas de la clase Business
        System.out.println("El número total de reservas de la clase Business es de: " +
                GestorReservas.cantidadReservasClase("Business"));
    }
}
