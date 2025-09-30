Documentacion 
---

```markdown
# Proyecto: Crear Archivo de Reservas en Java

## 📖 Descripción
Este proyecto implementa un programa en **Java** que permite **crear o actualizar un archivo de texto llamado `reservas.txt`**.  
El archivo almacena información de reservas de un vuelo (ID de reserva, nombre del pasajero, número de vuelo y asiento).  

El programa garantiza que:
- Si el archivo **no existe**, se crea automáticamente en la carpeta raíz del proyecto (donde está el código fuente).
- Si el archivo **ya existe**, se abre en modo *append* (añadir al final) sin borrar lo previamente guardado.
- Los errores se gestionan de forma controlada, mostrando mensajes claros al usuario.

---

## ⚙️ Tecnologías utilizadas
- **Lenguaje:** Java 8 o superior  
- **IDE recomendado:** IntelliJ IDEA (para compilar y ejecutar el programa de forma sencilla)  
- **Clases Java usadas:**
  - `BufferedWriter`: escritura eficiente de texto en archivos.
  - `FileWriter`: apertura/creación de archivos en modo escritura.
  - `FileNotFoundException`: excepción si no se encuentra la ruta especificada.
  - `IOException`: excepción general de entrada/salida.

---

## 📂 Estructura del proyecto
```

CrearArchivoReservas/
├── src/
│   └── CrearArchivoReservas.java
├── reservas.txt   (se generará automáticamente al ejecutar el programa)

````

---

## 📌 Código principal

```java
// CrearArchivoReservas.java
// Programa para crear/añadir reservas en "reservas.txt" con manejo de errores y mensajes claros.

import java.io.BufferedWriter;        // Para escribir texto de manera eficiente
import java.io.FileNotFoundException; // Excepción si la ruta no existe (carpeta inválida)
import java.io.IOException;           // Excepción general de entrada/salida
import java.io.FileWriter;            // Para abrir/crear el archivo

public class CrearArchivoReservas {

    public static void main(String[] args) {
        // Nombre del archivo de texto (se creará en la carpeta raíz del proyecto en IntelliJ)
        String nombreArchivo = "reservas.txt";

        // try-with-resources: asegura que el archivo se cierre automáticamente
        // FileWriter(nombreArchivo, true) -> "true" significa abrir en modo append (añadir al final sin borrar lo existente)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {

            // Escribimos líneas de ejemplo (en un caso real, leerías datos del usuario)
            writer.write("ReservaID: 001 | Pasajero: Juan Pérez | Vuelo: MX123 | Asiento: 12A");
            writer.newLine(); // salto de línea

            writer.write("ReservaID: 002 | Pasajero: María López | Vuelo: MX123 | Asiento: 12B");
            writer.newLine();

            writer.write("ReservaID: 003 | Pasajero: Carlos Sánchez | Vuelo: MX456 | Asiento: 5C");
            writer.newLine();

            // Mensaje amigable en consola
            System.out.println("Archivo '" + nombreArchivo + "' creado/actualizado correctamente.");

        } catch (FileNotFoundException e) {
            // Error raro aquí (sólo ocurre si la carpeta no existe o no hay permisos)
            System.err.println("Error: No se encontró la ruta del archivo -> " + e.getMessage());

        } catch (IOException e) {
            // Errores generales de escritura (permisos, disco lleno, etc.)
            System.err.println("Error al crear o escribir en el archivo -> " + e.getMessage());
        }
    }
}
````

---

## ▶️ Ejecución en IntelliJ IDEA

1. **Abrir IntelliJ IDEA.**
2. Crear un nuevo **Proyecto Java** (sin frameworks adicionales).
3. En la carpeta `src/`, crear un archivo llamado `CrearArchivoReservas.java`.
4. Copiar el código anterior dentro del archivo.
5. Compilar y ejecutar desde IntelliJ con:

    * Botón derecho sobre el archivo → **Run 'CrearArchivoReservas.main()'**.
6. En la carpeta raíz del proyecto se generará automáticamente el archivo `reservas.txt`.

---

## 📊 Ejemplo de salida

### Consola

```
Archivo 'reservas.txt' creado/actualizado correctamente.
```

### Contenido de `reservas.txt`

```
ReservaID: 001 | Pasajero: Juan Pérez | Vuelo: MX123 | Asiento: 12A
ReservaID: 002 | Pasajero: María López | Vuelo: MX123 | Asiento: 12B
ReservaID: 003 | Pasajero: Carlos Sánchez | Vuelo: MX456 | Asiento: 5C
```

---

## 🧩 Explicación del código

* **Clase principal:** `CrearArchivoReservas`.
* **Método `main`:** punto de entrada del programa.
* **`try-with-resources`:** asegura que el archivo se cierre automáticamente (similar a `with open(...)` en Python).
* **Modo append (`true` en `FileWriter`):** añade información al final del archivo en lugar de sobrescribir lo existente.
* **Gestión de excepciones:**

    * `FileNotFoundException`: se lanza si la ruta no existe o no hay permisos de acceso.
    * `IOException`: abarca problemas como permisos denegados, disco lleno o errores al escribir.

---

## 🤖 Uso de Inteligencia Artificial

Durante la implementación se utilizó **ChatGPT (OpenAI)** como apoyo para:

* Adaptar explicaciones de Python a Java.
* Documentar línea por línea el código para comprensión académica.
* Generar este archivo `README.md` con instrucciones detalladas.

La IA fue utilizada como **herramienta de asistencia**, mientras que el código fue revisado, probado y ajustado manualmente por las autoras.

---

## 👥 Autoras

* **Carmen Victoria Casas Novas García**
* **Natalia Gámez Barea**

```

---

✅ Con esta versión:  
- El formato es más limpio y profesional.  
- Secciones están bien separadas con títulos y ejemplos.  
- Se ve académico (explicación técnica, pasos, ejemplos, autoría y uso de IA).  

¿Quieres que además le añada un apartado de **"Limitaciones y posibles mejoras"** (ejemplo: leer datos del usuario en vez de escribir fijos), para darle un extra académico?
```
