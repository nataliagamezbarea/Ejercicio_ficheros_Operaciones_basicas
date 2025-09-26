package Ejercicio2;

import sistemaGestionVuelos.GestorArchivos;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar reservas de vuelos.
 * Permite crear un archivo maestro y luego separar las reservas por destino.
 */
public class SistemaReservas {

    /**
     * Contador simple de reservas por destino.
     */
    static class ContadorDestino {
        String destino;
        int cantidad;

        public ContadorDestino(String destino) {
            this.destino = destino;
            this.cantidad = 0;
        }
    }

    /**
     * Crea un archivo maestro con todas las reservas.
     */
    public void crearArchivoMaestro(String archivo, List<Reserva> reservas) {
        GestorArchivos.crearArchivoSiNoExiste(archivo);
        GestorArchivos.escribirArchivo(archivo, "Número de asiento, Nombre del pasajero, Clase de vuelo, Destino");
        for (Reserva r : reservas) {
            GestorArchivos.appendArchivo(archivo, r.toString());
        }
        System.out.println("Archivo maestro creado: " + archivo);
    }

    /**
     * Genera el nombre de archivo para un destino.
     */
    public String nombreArchivoDestino(String destino) {
        return "reservas_" + destino.toLowerCase() + ".txt";
    }

    /**
     * Escribe una reserva en el archivo del destino usando GestorArchivos.
     */
    public void escribirArchivoDestino(String destino, String linea) {
        String archivoDestino = nombreArchivoDestino(destino);
        boolean existe = new java.io.File(archivoDestino).exists();
        if (!existe) {
            GestorArchivos.escribirArchivo(archivoDestino, "Número de asiento, Nombre del pasajero, Clase de vuelo, Destino");
        }
        GestorArchivos.appendArchivo(archivoDestino, linea);
    }

    /**
     * Actualiza el contador de reservas por destino.
     */
    public void actualizarContador(List<ContadorDestino> contadores, String destino) {
        for (ContadorDestino cd : contadores) {
            if (cd.destino.equalsIgnoreCase(destino)) {
                cd.cantidad++;
                return;
            }
        }
        ContadorDestino nuevo = new ContadorDestino(destino);
        nuevo.cantidad = 1;
        contadores.add(nuevo);
    }

    /**
     * Muestra resumen de reservas por destino.
     */
    public void mostrarResumen(List<ContadorDestino> contadores) {
        System.out.println("Resumen de reservas por destino:");
        for (ContadorDestino cd : contadores) {
            System.out.println(cd.destino + ": " + cd.cantidad + " reserva(s)");
        }
    }

    /**
     * Clasifica las reservas por destino usando GestorArchivos.
     * Registra errores si faltan campos.
     */
    public void clasificarReservas(String archivoMaestro) {
        List<ContadorDestino> contadores = new ArrayList<>();
        List<String> lineas = GestorArchivos.leerArchivoLineas(archivoMaestro);

        if (lineas.isEmpty()) {
            System.out.println("Archivo maestro vacío: " + archivoMaestro);
            return;
        }

        String encabezado = lineas.get(0);
        int indiceDestino = -1;
        String[] columnas = encabezado.split(",\\s*");
        for (int i = 0; i < columnas.length; i++) {
            if (columnas[i].equalsIgnoreCase("Destino")) {
                indiceDestino = i;
                break;
            }
        }
        if (indiceDestino == -1) {
            System.out.println("No se encontró columna 'Destino'");
            return;
        }

        // Procesar reservas (saltando el encabezado)
        for (int i = 1; i < lineas.size(); i++) {
            String linea = lineas.get(i).trim();
            if (linea.isEmpty()) continue; // Ignorar líneas vacías
            String[] datos = linea.split(",\\s*");
            if (datos.length <= indiceDestino) {
                GestorArchivos.registrarError(linea, "Falta algún campo en la reserva");
                continue;
            }
            String destino = datos[indiceDestino];
            escribirArchivoDestino(destino, linea);
            actualizarContador(contadores, destino);
        }

        mostrarResumen(contadores);
    }
}
