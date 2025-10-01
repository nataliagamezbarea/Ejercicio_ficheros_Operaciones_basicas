package Ejercicio1;

import GestorArchivos.GestorArchivos;
import java.util.List;

/**
 * Gestión simple de reservas.
 */
public class SistemaReservas {
    public static void main(String[] args) {
        String archivo = "reservas.txt";

        // Crear archivo si no existe
        GestorArchivos.crearArchivoSiNoExiste(archivo);

        // Escribir reservas directamente
        GestorArchivos.escribirArchivo(archivo, "12A, Juan Pérez, Economy");
        GestorArchivos.appendArchivo(archivo, "14B, María López, Business");
        GestorArchivos.appendArchivo(archivo, "21C, Carlos García, Economy");

        // Leer reservas
        List<String> reservas = GestorArchivos.leerArchivoLineas(archivo);

        System.out.println("\n=== LISTA DE RESERVAS ===");

        int totalReservas = 0;
        int businessCount = 0;

        // Recorrer cada reserva
        for (String reserva : reservas) {
            totalReservas++;

            // Mostrar línea completa (más simple y natural)
            System.out.println(reserva);

            // Contar Business
            if (reserva.toLowerCase().contains("business")) {
                businessCount++;
            }
        }

        // Resultados finales
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Número total de reservas: " + totalReservas);
        System.out.println("Pasajeros en clase Business: " + businessCount);
    }
}
