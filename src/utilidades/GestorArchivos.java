package utilidades;

import java.io.FileWriter;
import java.io.IOException;


public class GestorArchivos {
    /**
     * Esta función crea un archivo manejando todos los casos de excepciones , si el archivo ya existe no se va a crear dos veces.
     * @param nombre_archivo el nombre del archivo que se crea es el pasado por parámetro
     */
    public static void crearArchivo(String nombre_archivo) {
        try {
            FileWriter escribir = new FileWriter(nombre_archivo, true);
            escribir.close();
        } catch (SecurityException e) {
            System.out.println("No tienes permisos para escribir en esta carpeta");
        } catch (NullPointerException e) {
            System.out.println("El nombre del fichero no puede ser nulo");
        } catch (IOException e) {
            System.out.println("Ha habido problemas de entrada y salida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha habido problemas generales");
        }
    }
}
