package utilidades;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            System.out.println("Ha habido problemas generales: " + e.getMessage());
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
            BufferedReader lector = new BufferedReader(new FileReader(nombre_archivo));
            List<String> listaColumnas = Arrays.asList(lector.readLine().split(","));

            int numeroAsientoCol = listaColumnas.indexOf("NumeroAsiento");
            int nombrePasajeroCol = listaColumnas.indexOf("NombrePasajero");
            int claseCol = listaColumnas.indexOf("Clase");
            int destinoCol = listaColumnas.indexOf("Destino");

            if (numeroAsientoCol == -1 || nombrePasajeroCol == -1 || claseCol == -1) {
                System.out.println("Error: el archivo debe contener los encabezados 'NumeroAsiento, NombrePasajero, Clase'.");
                lector.close();
                return;
            }

            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");

                if (destinoCol != -1) {
                    new Reservas(datos[numeroAsientoCol].trim(), datos[nombrePasajeroCol].trim(), datos[claseCol].trim(), datos[destinoCol].trim());
                } else {
                    new Reservas(datos[numeroAsientoCol].trim(), datos[nombrePasajeroCol].trim(), datos[claseCol].trim());
                }
            }
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


    /**
     * Procesa un archivo de reservas con posibles errores.
     * - Valida cada línea: debe tener 4 campos (asiento, nombre, clase, destino).
     * - Si la línea es válida, crea la reserva y la escribe en su archivo de destino.
     * - Si falta algún campo o la línea está vacía, la registra en registro_errores.log.
     */

    public static void procesarReservasConErrores(String archivoMaestro) {
        String archivoErrores = "src/Ejercicio3/registro_errores.log";
        crearArchivo(archivoErrores); // Crear archivo de errores si no existe

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoMaestro))) {
            String encabezados = lector.readLine(); // Ignoramos encabezados

            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");

                // Array con los nombres de los campos esperados
                String[] nombresCampos = {"NumeroAsiento", "NombrePasajero", "Clase", "Destino"};

                // Creamos un array de 4 elementos para poder identificar campos faltantes
                String[] datosCompletos = new String[4];
                for (int i = 0; i < 4; i++) {
                    if (i < datos.length) {
                        datosCompletos[i] = datos[i].trim();
                    } else {
                        datosCompletos[i] = ""; // Si no hay dato, ponemos vacío
                    }
                }

                boolean lineaValida = true;
                StringBuilder descripcionError = new StringBuilder();

                // Revisamos cada campo
                for (int i = 0; i < 4; i++) {
                    if (datosCompletos[i].isEmpty()) {
                        lineaValida = false;
                        if (!descripcionError.isEmpty()) {
                            descripcionError.append("; "); // Separador si hay más de un error
                        }
                        descripcionError.append("Falta el campo '").append(nombresCampos[i]).append("'");
                    }
                }

                if (!lineaValida) {
                    // Añadimos fecha y hora actual
                    String fechaHora = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                    escribirArchivo(archivoErrores, fechaHora + ", " + linea + ", " + descripcionError.toString());
                    continue; // Saltamos la línea inválida
                }

                // Línea válida: creamos la reserva
                new Reservas(datosCompletos[0], datosCompletos[1], datosCompletos[2], datosCompletos[3]);

                // Crear archivo de destino según el destino de la reserva
                String archivoPorDestino = "src/Ejercicio3/reserva_" + datosCompletos[3].toLowerCase() + ".txt";
                crearArchivo(archivoPorDestino);

                // Guardamos la línea válida en el archivo correspondiente
                escribirArchivo(archivoPorDestino, linea);
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }


    /**
     * Muestra el contenido del archivo de registro de errores por consola
     *
     * @param archivoErrores Ruta del archivo de registro de errores
     */
    public static void mostrarRegistroErrores(String archivoErrores) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoErrores))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea); // Se muestra línea por línea
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de errores no existe.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
