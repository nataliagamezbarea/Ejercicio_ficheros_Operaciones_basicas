package Ejercicio1;

import utilidades.GestorArchivos;

public class SistemaReservas {
    public static void main(String[] args) {
        // variable para el nombre del archivo
        String nombre_archivo = "src/Ejercicio1/reservas.txt" ;

        // crear archivo
        GestorArchivos.crearArchivo(nombre_archivo);

        // comprobar los encabezados del archivo
        GestorArchivos.comprobarEncabezados(nombre_archivo , "NumeroAsiento,NombrePasajero,Clase");
        // hacemos una lista de los datos a escribir
        String [] datosEscribir = {
                "12A, Juan Pérez, Economy",
                "14B, María López, Business",
                "21C, Carlos García, Economy"
        };

        // recorremos toda la lista y escribimos linia por linia en el archivo
        for (String linia: datosEscribir) {
            GestorArchivos.escribirArchivo(nombre_archivo,linia);
        }

    }
}
