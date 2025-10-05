package Ejercicio2;

import utilidades.GestorArchivos;
import utilidades.Reservas;

public class SistemaGestionVueloConErrores {
    public static void main(String[] args) {
        // Archivo maestro con posibles errores
        String archivoMaestro = "src/Ejercicio3/reservas_maestro_con_errores.txt";

        // Crear el archivo si no existe
        GestorArchivos.crearArchivo(archivoMaestro);

        // Comprobar encabezados
        GestorArchivos.comprobarEncabezados(archivoMaestro, "NumeroAsiento,NombrePasajero,Clase,Destino");

        // Datos de ejemplo (con algunas líneas erróneas)
        String[] datosReservas = {
                "12A, Juan Pérez, Economy, Madrid",         // correcta
                "14B, María López, Business",             // falta destino
                "21C, Carlos García, Economy, Madrid",    // correcta
                "05D, , Business, Londres",               // falta nombre
                "19E, Luis Gómez, Economy, París",        // correcta
                "08F, Sofía Vargas, Economy, " ,          // falta destino
                " , Ana Sánchez, Business, Londres",      // falta número asiento
                "10G, Jorge,Economy,Lisboa",              // correcta, sin espacios extra
                "11H, , , "                               // completamente vacía excepto comas
        };

        // Escribir datos iniciales
        for (String linea : datosReservas) {
            GestorArchivos.escribirArchivo(archivoMaestro, linea);
        }

        // Procesar reservas validando errores
        GestorArchivos.procesarReservasConErrores(archivoMaestro);

        // Mostrar resumen por destino
        for (String destino : Reservas.getDestinos()) {
            int cantidad = Reservas.getCantidadPorDestino(destino);
            System.out.println("Archivo: reserva_" + destino.toLowerCase() + ".txt - Total reservas: " + cantidad);
        }

        // Mostrar contenido del registro de errores
        System.out.println("\nContenido del registro de errores:");
        GestorArchivos.mostrarRegistroErrores("src/Ejercicio3/registro_errores.log");
    }
}
