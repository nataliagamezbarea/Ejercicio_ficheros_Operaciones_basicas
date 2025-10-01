package Ejercicio3;

import GestorArchivos.GestorArchivos;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SistemaGestionVuelos {

    public static void main(String[] args) {

        // Datos de ejemplo (algunos con errores)
        String[][] datos = {
                {"12A", "Juan Pérez", "Economy", "Madrid"},
                {"14B", "María López", "Business", "París"},
                {"21C", "Carlos García", "Economy"},   // Falta destino
                {"05D", "Ana Sánchez", "Business", "Londres"},
                {"19E", "Luis Gómez", "Economy", "París"},
                {"08F", "Sofía Vargas", "Economy"},    // Falta destino
                {}                                     // Línea vacía
        };

        List<Reserva> reservas = new ArrayList<>();
        for (String[] r : datos) {
            reservas.add(new Reserva(
                    r.length > 0 ? r[0] : "",
                    r.length > 1 ? r[1] : "",
                    r.length > 2 ? r[2] : "",
                    r.length > 3 ? r[3] : ""
            ));
        }

        String archivoMaestro = "reservas_maestro_con_errores.txt";
        GestorArchivos.crearArchivoSiNoExiste(archivoMaestro);

        for (Reserva r : reservas) {
            GestorArchivos.appendArchivo(archivoMaestro, r.toString());
        }

        clasificarYContarPorDestino(archivoMaestro);
    }

    public static void clasificarYContarPorDestino(String archivoMaestro) {
        List<String> lineas = GestorArchivos.leerArchivoLineas(archivoMaestro);
        List<String> destinos = new ArrayList<>();
        List<Integer> cantidad = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String logFile = "registro_errores.log";

        for (String linea : lineas) {
            String[] campos = linea.split(", ");

            if (campos.length < 4) {
                String faltante = switch (campos.length) {
                    case 0 -> "asiento, nombre, clase y destí";
                    case 1 -> "nombre, clase y destí";
                    case 2 -> "clase y destí";
                    case 3 -> "destí";
                    default -> "desconocido";
                };
                String log = dtf.format(LocalDateTime.now()) + ", " +
                        (linea.isEmpty() ? "(línea vacía)" : linea) +
                        ", Falta el camp '" + faltante + "'.";
                GestorArchivos.appendArchivo(logFile, log);
                continue;
            }

            String destino = campos[3];
            String archivoDestino = "reservas_" + destino.toLowerCase() + ".txt";
            GestorArchivos.appendArchivo(archivoDestino, linea);

            int index = destinos.indexOf(destino);
            if (index == -1) {
                destinos.add(destino);
                cantidad.add(1);
            } else {
                cantidad.set(index, cantidad.get(index) + 1);
            }
        }

        System.out.println("\nResumen de archivos por destino:");
        for (int i = 0; i < destinos.size(); i++) {
            System.out.println("reservas_" + destinos.get(i).toLowerCase() + ".txt -> " + cantidad.get(i) + " reservas");
        }

        System.out.println("\nContenido de registro_errores.log:");
        for (String error : GestorArchivos.leerArchivoLineas(logFile)) {
            System.out.println(error);
        }
    }
}
