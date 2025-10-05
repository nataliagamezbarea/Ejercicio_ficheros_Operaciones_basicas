package Ejercicio2;

import utilidades.GestorArchivos;
import utilidades.Reservas;

import java.util.List;


public class SistemaGestionVuelo {
    public static void main(String[] args) {
        // variable para el nombre del archivo
        String nombre_archivo = "src/Ejercicio2/reservas_maestro.txt" ;


        // crear archivo
        GestorArchivos.crearArchivo(nombre_archivo);


        // comprobar los encabezados del archivo
        GestorArchivos.comprobarEncabezados(nombre_archivo , "NumeroAsiento,NombrePasajero,Clase,Destino");
        // hacemos una lista de los datos a escribir
        String [] datosEscribir = {
                "12A, Juan Pérez, Economy, Madrid",
                "14B, María López, Business, París",
                "21C, Carlos García, Economy, Madrid",
                "05D, Ana Sánchez, Business, Londres",
                "19E, Luis Gómez, Economy, París",
                "08F, Sofía Vargas, Economy, Londres"
        };


        // recorremos toda la lista y escribimos linia por linia en el archivo
        for (String linia: datosEscribir) {
            GestorArchivos.escribirArchivo(nombre_archivo,linia);
        }
        // que cree el archivo y crea instancias desde el archivo
        GestorArchivos.leeryCrearInstanciasDesdeArchivo(nombre_archivo);

        for (String destino : Reservas.getDestinos()) {
            String archivoDestino = "src/Ejercicio2/reserva_" + destino.toLowerCase() + ".txt";
            GestorArchivos.crearArchivo(archivoDestino);
        }


        for (Reservas r : Reservas.getReservas()) {
            String destino = r.getDestino().toLowerCase(); // destino en minúsculas
            String archivoDestino = "src/Ejercicio2/reserva_" + destino + ".txt";

            // Crear el archivo si no existe
            GestorArchivos.crearArchivo(archivoDestino);

            // Escribir la reserva en el archivo correspondiente
            GestorArchivos.escribirArchivo(archivoDestino, r.getNumeroAsiento() + ", " + r.getNombrePasajero() + ", " + r.getClase() + ", " + r.getDestino());
        }



    }
}
