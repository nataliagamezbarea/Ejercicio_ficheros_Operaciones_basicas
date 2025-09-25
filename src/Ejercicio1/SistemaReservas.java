package Ejercicio1;

import utilidades.GestorArchivos;

public class SistemaReservas {
    public static void main(String[] args) {
        // variable para el nombre del archivo
        String nombre_archivo = "src/Ejercicio1/reservas.txt" ;

        // crear archivo
        GestorArchivos.crearArchivo(nombre_archivo);
    }
}
