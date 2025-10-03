package Ejercicio1;


import utilidades.GestorArchivos;
import utilidades.Reservas;


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
                "12A,Juan Pérez,Economy",
                "14B,María López,Business",
                "21C,Carlos García,Economy"
        };


        // recorremos toda la lista y escribimos linia por linia en el archivo
        for (String linia: datosEscribir) {
            GestorArchivos.escribirArchivo(nombre_archivo,linia);
        }
        // que cree el archivo y crea instancias desde el archivo
        GestorArchivos.leeryCrearInstanciasDesdeArchivo(nombre_archivo);
        // muestra todas las reservas
        Reservas.getMostrarReservas();
        // ahora muestra la cantidad de las reservas
        System.out.println("El numero total de reservas es de: " +  Reservas.getTotalReservas());
        // ahora utiliza la funcion para obtener el total de las reservas de una clase
        System.out.println("El número total de reservas de la clase Business es de: " + Reservas.getCantidadReservasClase("Business"));
    }
}
