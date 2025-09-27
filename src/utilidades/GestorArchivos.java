package utilidades;

import java.io.*;


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

    /**
     * Esta función permite escribir un archivo  manejando todos los casos de excepciones , si el archivo no existe va a dar error y además
     * se añadirá al escribir cada linia una linia de salto al final
     * @param nombre_archivo el nombre del archivo que se  añade la linia que el usuario ha pasado por parametro
     * @param linia es el texto que se va a escribir en el archivo
     */
    public static void escribirArchivo (String nombre_archivo, String linia) {
        try {
            FileWriter escritor = new FileWriter(nombre_archivo , true);
            escritor.write(linia + "\n");
            escritor.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
        }
        catch (SecurityException e) {
            System.out.println("No tienes permisos para escribir en esta carpeta");
        } catch (NullPointerException e) {
            System.out.println("El nombre del fichero no puede ser nulo");
        } catch (IOException e) {
            System.out.println("Ha habido problemas de entrada y salida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha habido problemas generales");
        }
    }

    /**
     * Comprueba después de crear el archivo si el archivo está vacio y tiene encabezados y si no tiene los agrega
     * @param nombre_archivo el nombre del archivo que se  añade los encabezados que el usuario ha pasado por parametro
     * @param encabezados permite identificar la información de la columna por ejemplo : nombrePasajero
     */
    public static void comprobarEncabezados (String nombre_archivo , String encabezados) {
        File archivo = new File(nombre_archivo);
        if (archivo.length() == 0) {
            GestorArchivos.escribirArchivo(nombre_archivo , encabezados);
        }
    }
}

