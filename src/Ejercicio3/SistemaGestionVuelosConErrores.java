package Ejercicio3;

import java.util.ArrayList;
import java.util.List;
import GestorArchivos.GestorArchivos;

/**
 * Programa para gestionar reservas de vuelos (Parte 3).
 * <p>
 * Esta clase hace lo siguiente:
 * - Define un conjunto de reservas de ejemplo, algunas con errores.
 * - Crea un archivo maestro con todas las reservas.
 * - Clasifica las reservas por destino, creando un archivo por cada destino.
 * - Registra automáticamente las reservas inválidas en un log de errores.
 * - Muestra un resumen con la cantidad de reservas por destino y los errores registrados.
 */
public class SistemaGestionVuelosConErrores {

    public static void main(String[] args) {

        // Datos de ejemplo: asiento, nombre, clase y destino
        String[][] datos = {
                {"12A", "Juan Pérez", "Economy", "Madrid"},
                {"14B", "María López", "Business"},           // ERROR: falta destino
                {"21C", "Carlos García", "Economy", "Madrid"},
                {"05D", "Ana Sánchez", "Business", "Londres"},
                {"19E", "Luis Gómez"},                        // ERROR: falta clase y destino
                {"08F", "Sofía Vargas", "Economy", "Londres"},
        };

        // Crear lista de objetos Reserva
        List<Reserva> reservas = new ArrayList<>();
        for (String[] r : datos) {
            // Inicializar los campos vacíos
            String asiento = "";
            String nombre = "";
            String clase = "";
            String destino = "";

            // Rellenar los campos que existen
            if (r.length > 0) asiento = r[0];
            if (r.length > 1) nombre = r[1];
            if (r.length > 2) clase = r[2];
            if (r.length > 3) destino = r[3];

            reservas.add(new Reserva(asiento, nombre, clase, destino));
        }

        // Nombre del archivo maestro
        String archivoMaestro = "reservas_maestro_con_errores.txt";

        // Crear el archivo maestro si no existe
        GestorArchivos.crearArchivoSiNoExiste(archivoMaestro);

        // Escribir todas las reservas en el archivo maestro
        for (Reserva r : reservas) {
            GestorArchivos.appendArchivo(archivoMaestro, r.toString());
        }
        System.out.println("\nArchivo maestro '" + archivoMaestro + "' creado con éxito.\n");

        // Clasificar reservas por destino, mostrar resumen y registrar errores
        clasificarYContarPorDestino(archivoMaestro);
    }

    /**
     * Clasifica las reservas por destino y registra errores de líneas incompletas.
     * <p>
     * Por cada reserva del archivo maestro:
     * - Se crea un archivo por destino (si no existe se crea).
     * - Se guarda la reserva en el archivo correspondiente.
     * - Si falta algún campo, se registra en registro_errores.log.
     * - Se lleva un contador de cuántas reservas válidas hay por destino.
     *
     * @param archivoMaestro Nombre del archivo maestro que contiene todas las reservas.
     */
    public static void clasificarYContarPorDestino(String archivoMaestro) {
        // Leer todas las líneas del archivo maestro
        List<String> lineas = GestorArchivos.leerArchivoLineas(archivoMaestro);

        // Listas para llevar control de los destinos y la cantidad de reservas válidas
        List<String> destinosProcesados = new ArrayList<>();
        List<Integer> cantidadPorDestino = new ArrayList<>();

        for (String linea : lineas) {
            String[] datosLinea = linea.split(", ");

            // Si no tiene los 4 campos, registrar error y continuar
            if (datosLinea.length < 4) {
                String campoFaltante = determinarCampoFaltante(datosLinea.length);
                GestorArchivos.registrarError(linea, "Falta el campo '" + campoFaltante + "'");
                continue;
            }

            // Guardar la reserva en el archivo del destino correspondiente
            String destino = datosLinea[3];
            String archivoDestino = "reservas_" + destino.toLowerCase() + ".txt";
            GestorArchivos.appendArchivo(archivoDestino, linea);

            // Contar reservas por destino
            int index = destinosProcesados.indexOf(destino);
            if (index == -1) {
                destinosProcesados.add(destino);
                cantidadPorDestino.add(1);
            } else {
                cantidadPorDestino.set(index, cantidadPorDestino.get(index) + 1);
            }
        }

        // Mostrar resumen de archivos por destino
        System.out.println("\nResumen de archivos por destino:");
        for (int i = 0; i < destinosProcesados.size(); i++) {
            String archivoDestino = "reservas_" + destinosProcesados.get(i).toLowerCase() + ".txt";
            System.out.println(archivoDestino + " -> " + cantidadPorDestino.get(i) + " reservas");
        }

        // Mostrar errores registrados
        mostrarErrores();
    }

    /**
     * Determina cuál campo falta según el número de datos presentes en la línea.
     *
     * @param camposPresentes Cantidad de campos encontrados en la línea.
     * @return Nombre del campo faltante.
     */
    private static String determinarCampoFaltante(int camposPresentes) {
        switch (camposPresentes) {
            case 0: return "asiento";
            case 1: return "nombre";
            case 2: return "clase";
            case 3: return "destino";
            default: return "desconocido";
        }
    }

    /**
     * Muestra el contenido del archivo registro_errores.log en consola.
     */
    private static void mostrarErrores() {
        List<String> errores = GestorArchivos.leerArchivoLineas("registro_errores.log");
        if (!errores.isEmpty()) {
            System.out.println("\nRegistro de errores:");
            for (String e : errores) {
                System.out.println(e);
            }
        }
    }
}
