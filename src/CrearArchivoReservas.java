// CrearArchivoReservas.java
// Programa para crear/añadir reservas en "reservas.txt" con manejo de errores y mensajes claros.

import java.io.BufferedWriter;        // Para escribir texto de manera eficiente
import java.io.FileNotFoundException; // Excepción si la ruta no existe (carpeta inválida)
import java.io.IOException;           // Excepción general de entrada/salida
import java.io.FileWriter;            // Para abrir/crear el archivo

/**
 * Clase principal que permite crear o añadir reservas a un archivo de texto.
 * Cada reserva contiene un ID, el nombre del pasajero, el número de vuelo y el asiento.
 *
 * El archivo se llama "reservas.txt" y se almacena en la carpeta raíz del proyecto.
 * - Si el archivo no existe, se creará automáticamente.
 * - Si ya existe, se abrirá en modo append para no borrar el contenido previo.
 *
 * Ejemplo de uso:
 * <pre>
 *     CrearArchivoReservas.agregarReserva("004", "Ana Torres", "MX789", "8D");
 * </pre>
 *
 * Autoras:
 * - Carmen Victoria Casas Novas García
 * - Natalia Gámez Barea
 */
public class CrearArchivoReservas {

    /** Nombre del archivo donde se almacenan las reservas */
    private static final String NOMBRE_ARCHIVO = "reservas.txt";

    /**
     * Método reutilizable para añadir una reserva al archivo.
     *
     * @param reservaId   Identificador único de la reserva
     * @param pasajero    Nombre del pasajero
     * @param vuelo       Número de vuelo
     * @param asiento     Número de asiento
     */
    public static void agregarReserva(String reservaId, String pasajero, String vuelo, String asiento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            writer.write("ReservaID: " + reservaId + " | Pasajero: " + pasajero +
                    " | Vuelo: " + vuelo + " | Asiento: " + asiento);
            writer.newLine();
            System.out.println("Reserva agregada correctamente: " + reservaId);
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró la ruta del archivo -> " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al crear o escribir en el archivo -> " + e.getMessage());
        }
    }

    /**
     * Método principal para probar el programa.
     *
     * @param args No se utilizan en este ejemplo
     */
    public static void main(String[] args) {
        // Ejemplo de uso del método reutilizable
        agregarReserva("001", "Juan Pérez", "MX123", "12A");
        agregarReserva("002", "María López", "MX123", "12B");
        agregarReserva("003", "Carlos Sánchez", "MX456", "5C");

        System.out.println("Archivo '" + NOMBRE_ARCHIVO + "' creado/actualizado correctamente.");
    }
}
