package sistemaGestionVuelos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase utilitaria para la gestión de archivos de texto.
 * Permite crear, leer, escribir, añadir contenido y registrar errores.
 * Diseñada para ser reutilizable en la gestión de reservas de vuelos.
 */
public class GestorArchivos {

    /**
     * Crea un archivo vacío si no existe.
     *
     * @param nombreArchivo Nombre del archivo a crear.
     */
    public static void crearArchivoSiNoExiste(String nombreArchivo) {
        try {
            FileWriter fw = new FileWriter(nombreArchivo, true); // "true" para no sobrescribir si ya existe
            fw.close();
            System.out.println("Archivo '" + nombreArchivo + "' listo.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo '" + nombreArchivo + "': " + e.getMessage());
        }
    }

    /**
     * Escribe o sobrescribe contenido en un archivo.
     *
     * @param nombreArchivo Nombre del archivo.
     * @param contenido     Contenido a escribir.
     */
    public static void escribirArchivo(String nombreArchivo, String contenido) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.println(contenido);
            System.out.println("Se escribió en el archivo '" + nombreArchivo + "'.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo '" + nombreArchivo + "': " + e.getMessage());
        }
    }

    /**
     * Añade contenido al final de un archivo.
     *
     * @param nombreArchivo Nombre del archivo.
     * @param contenido     Contenido a añadir.
     */
    public static void appendArchivo(String nombreArchivo, String contenido) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            pw.println(contenido);
            System.out.println("Se añadió al archivo '" + nombreArchivo + "'.");
        } catch (IOException e) {
            System.out.println("Error al añadir al archivo '" + nombreArchivo + "': " + e.getMessage());
        }
    }

    /**
     * Lee un archivo línea por línea y devuelve una lista de Strings.
     *
     * @param nombreArchivo Nombre del archivo.
     * @return Lista de líneas.
     */
    public static List<String> leerArchivoLineas(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
            System.out.println("Se leyó el archivo '" + nombreArchivo + "' correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo '" + nombreArchivo + "': " + e.getMessage());
        }
        return lineas;
    }

    /**
     * Registra un error en un archivo de log con la fecha y hora actual.
     *
     * @param linea       Línea que causó el error.
     * @param descripcion Descripción del error.
     */
    public static void registrarError(String linea, String descripcion) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("registro_errores.log", true))) {
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String error = fechaHora + ", " + linea + ", " + descripcion;
            pw.println(error);
            System.out.println("Error registrado: " + error);
        } catch (IOException e) {
            System.out.println("Error al registrar el error: " + e.getMessage());
        }
    }
}
