package Ejercicio2;

// Clase que representa una reserva de vuelo
// Cada objeto de esta clase guarda la información de un pasajero y su asiento
public class Reserva {

    // ATRIBUTOS: datos que cada reserva va a almacenar
    private final String asiento;      // Número de asiento del pasajero (ej: "12A")
    private final String nombre;       // Nombre del pasajero
    private final String claseVuelo;   // Clase del vuelo (ej: "Economy" o "Business")
    private final String destino;      // Destino del vuelo (ej: "Madrid")

    // CONSTRUCTOR: se usa para crear un objeto Reserva con todos sus datos
    // Cuando creamos una nueva reserva, debemos pasar estos cuatro valores
    public Reserva(String asiento, String nombre, String claseVuelo, String destino) {
        this.asiento = asiento;         // Se guarda el asiento en el atributo correspondiente
        this.nombre = nombre;           // Se guarda el nombre
        this.claseVuelo = claseVuelo;   // Se guarda la clase del vuelo
        this.destino = destino;         // Se guarda el destino
    }

    // GETTER para el destino
    // Permite acceder al destino de la reserva desde fuera de la clase
    public String getDestino() {
        return destino;
    }

    // toString(): método que devuelve una representación en texto del objeto Reserva.
// Convierte los atributos del objeto en una cadena separada por comas (asiento, nombre, claseVuelo, destino).
// Para ver esta información basta con hacer un System.out.println(objetoReserva).
// Esto es útil para imprimir la reserva en consola o escribirla en un archivo.
    @Override
    public String toString() {
        // String.join une los atributos en una sola línea, separados por comas y espacios
        return String.join(", ", asiento, nombre, claseVuelo, destino);
    }

}
