// CrearArchivoReservas.java
// Programa para crear/añadir reservas en "reservas.txt" con manejo de errores y mensajes claros.

import java.io.BufferedWriter;        // Para escribir texto de manera eficiente
import java.io.FileNotFoundException; // Excepción si la ruta no existe (carpeta inválida)
import java.io.IOException;           // Excepción general de entrada/salida
import java.io.FileWriter;            // Para abrir/crear el archivo
 /*https://chatgpt.com/share/68db97ed-9378-8000-bf96-5493b7b2e026*/

public class CrearArchivoReservas {

    public static void main(String[] args) {
        // Nombre del archivo de texto (se creará en la carpeta raíz del proyecto en IntelliJ)
        String nombreArchivo = "reservas.txt";

        // try-with-resources: asegura que el archivo se cierre automáticamente
        // FileWriter(nombreArchivo, true) -> "true" significa abrir en modo append (añadir al final sin borrar lo existente)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {

            // Escribimos líneas de ejemplo (en un caso real, leerías datos del usuario)
            writer.write("ReservaID: 001 | Pasajero: Juan Pérez | Vuelo: MX123 | Asiento: 12A");
            writer.newLine(); // salto de línea

            writer.write("ReservaID: 002 | Pasajero: María López | Vuelo: MX123 | Asiento: 12B");
            writer.newLine();

            writer.write("ReservaID: 003 | Pasajero: Carlos Sánchez | Vuelo: MX456 | Asiento: 5C");
            writer.newLine();

            // Mensaje amigable en consola
            System.out.println("Archivo '" + nombreArchivo + "' creado/actualizado correctamente.");

        } catch (FileNotFoundException e) {
            // Error raro aquí (sólo ocurre si la carpeta no existe o no hay permisos)
            System.err.println("Error: No se encontró la ruta del archivo -> " + e.getMessage());

        } catch (IOException e) {
            // Errores generales de escritura (permisos, disco lleno, etc.)
            System.err.println("Error al crear o escribir en el archivo -> " + e.getMessage());
        }
    }
}
