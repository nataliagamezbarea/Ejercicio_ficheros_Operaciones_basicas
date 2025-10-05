package Ejercicio2;

import utilidades.GestorArchivos;
import utilidades.Reservas;

public class SistemaGestionVuelo {
    public static void main(String[] args) {
        // Nombre del archivo maestro donde pondremos todas las reservas
        String archivoMaestro = "src/Ejercicio2/reservas_maestro.txt";

        // Crear el archivo si no existe
        GestorArchivos.crearArchivo(archivoMaestro);

        // Comprobar que tenga los encabezados correctos
        GestorArchivos.comprobarEncabezados(archivoMaestro, "NumeroAsiento,NombrePasajero,Clase,Destino");

        // Datos de las reservas que vamos a escribir
        String[] datosReservas = {
                "12A, Juan Pérez, Economy, Madrid",
                "14B, María López, Business, París",
                "21C, Carlos García, Economy, Madrid",
                "05D, Ana Sánchez, Business, Londres",
                "19E, Luis Gómez, Economy, París",
                "08F, Sofía Vargas, Economy, Londres"
        };

        // Escribir cada línea de reserva en el archivo maestro
        for (String linea : datosReservas) {
            GestorArchivos.escribirArchivo(archivoMaestro, linea);
        }

        // Leer el archivo maestro y crear las instancias de Reservas
        GestorArchivos.leeryCrearInstanciasDesdeArchivo(archivoMaestro);

        // Crear archivos separados para cada destino y escribir las reservas correspondientes
        for (Reservas r : Reservas.getListaReservas()) {
            // Nombre del archivo para cada destino en minúsculas
            String archivoPorDestino = "src/Ejercicio2/reserva_" + r.getDestino().toLowerCase() + ".txt";

            // Crear el archivo si no existe
            GestorArchivos.crearArchivo(archivoPorDestino);

            // Datos de la reserva en formato de línea
            String datos = r.getNumeroAsiento() + ", " + r.getNombrePasajero() + ", " + r.getClase() + ", " + r.getDestino();

            // Escribir la línea en el archivo correspondiente
            GestorArchivos.escribirArchivo(archivoPorDestino, datos);
        }
    }
}
