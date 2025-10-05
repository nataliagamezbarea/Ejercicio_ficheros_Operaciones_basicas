package utilidades;

import java.io.*;
import java.util.Arrays;
import java.util.List;


public class GestorArchivos {
    /**
     * Esta función crea un archivo manejando todos los casos de excepciones , si el archivo ya existe no se va a crear dos veces.
     *
     * @param nombre_archivo el nombre del archivo que se crea es el pasado por parámetro
     */
    public static boolean crearArchivo(String nombre_archivo) {
        try {
            File archivo = new File(nombre_archivo);
            boolean existe = archivo.exists(); // true si ya existía
            FileWriter escribir = new FileWriter(archivo, true); // abrimos/creamos
            escribir.close();
            // devolvemos true solo si NO existía antes
            return !existe;
        } catch (SecurityException e) {
            System.out.println("No tienes permisos para escribir en esta carpeta");
        } catch (NullPointerException e) {
            System.out.println("El nombre del fichero no puede ser nulo");
        } catch (IOException e) {
            System.out.println("Ha habido problemas de entrada y salida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha habido problemas generales: " + e.getMessage());
        }
        return false;
    }


    /**
     * Esta función permite escribir un archivo  manejando todos los casos de excepciones , si el archivo no existe va a dar error y además
     * se añadirá al escribir cada linia una linia de salto al final
     *
     * @param nombre_archivo el nombre del archivo que se  añade la linia que el usuario ha pasado por parametro
     * @param linia          es el texto que se va a escribir en el archivo
     */
    public static void escribirArchivo(String nombre_archivo, String linia) {
        try {
            FileWriter escritor = new FileWriter(nombre_archivo, true);
            escritor.write(linia + "\n");
            escritor.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
        } catch (SecurityException e) {
            System.out.println("No tienes permisos para escribir en esta carpeta");
        } catch (NullPointerException e) {
            System.out.println("El nombre del fichero no puede ser nulo");
        } catch (IOException e) {
            System.out.println("Ha habido problemas de entrada y salida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha habido problemas generales" + e.getMessage());
        }
    }

    /**
     * comprueba después de crear el archivo si el archivo está vacio y tiene encabezados y si no tiene los agrega
     *
     * @param nombre_archivo el nombre del archivo que se  añade los encabezados que el usuario ha pasado por parametro
     * @param encabezados    permite identificar la información de la columna por ejemplo : nombrePasajero
     */
    public static void comprobarEncabezados(String nombre_archivo, String encabezados) {
        File archivo = new File(nombre_archivo);
        if (archivo.length() == 0) {
            GestorArchivos.escribirArchivo(nombre_archivo, encabezados);
        }
    }


    /**
     * Lee un archivo de reservas, valida que tenga los encabezados correctos
     * y crea instancias de la clase Reservas a partir de cada línea de datos.
     *
     * @param nombre_archivo La ruta completa del archivo de reservas.
     */
    public static void leeryCrearInstanciasDesdeArchivo(String nombre_archivo) {
        try {
            // Creamos un BufferedReader para leer el archivo línea por línea
            BufferedReader lector = new BufferedReader(new FileReader(nombre_archivo));

            // Leemos la primera línea del archivo (encabezados),
            // la separamos por comas y la convertimos en una lista para poder usar indexOf
            List<String> listaColumnas = Arrays.asList(lector.readLine().split(","));

            // Obtenemos la posición de cada columna clave
            int numeroAsientoCol = listaColumnas.indexOf("NumeroAsiento");
            int nombrePasajeroCol = listaColumnas.indexOf("NombrePasajero");
            int claseCol = listaColumnas.indexOf("Clase");
            int destinoCol = listaColumnas.indexOf("Destino");
            // Validamos que los encabezados existen; si falta alguno, mostramos un mensaje y salimos
            if (numeroAsientoCol == -1 || nombrePasajeroCol == -1 || claseCol == -1) {
                System.out.println("Error: el archivo debe contener los encabezados 'NumeroAsiento, NombrePasajero, Clase'.");
                lector.close(); // cerramos el lector antes de salir
                return;
            }

            // Leemos cada línea restante del archivo y creamos una instancia de Reservas
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Separamos los datos de la línea usando la coma como separador
                String[] datos = linea.split(",");

                // Creamos una nueva reserva usando los índices que encontramos antes
                // trim() elimina espacios al inicio o al final por seguridad
                if (destinoCol != -1) {
                    new Reservas(datos[numeroAsientoCol].trim(), datos[nombrePasajeroCol].trim(), datos[claseCol].trim(), datos[destinoCol].trim());
                } else {
                    new Reservas(datos[numeroAsientoCol].trim(), datos[nombrePasajeroCol].trim(), datos[claseCol].trim());
                }

            }

            // Cerramos el lector después de terminar de leer el archivo
            lector.close();

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe o la ruta es incorrecta.");
        } catch (NullPointerException e) {
            System.out.println("El nombre del archivo no puede estar vacío.");
        } catch (IOException e) {
            System.out.println("Ha habido problemas de entrada y salida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha habido un error inesperado: " + e.getMessage());
        }
    }

}

