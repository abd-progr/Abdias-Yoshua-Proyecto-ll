# Clínica Veterinaria - Simulación con Estructuras Propias en Java

Este proyecto simula una clínica veterinaria con una interfaz gráfica desarrollada en Java Swing. Se han implementado desde cero dos estructuras de datos: una **lista enlazada** y un **árbol binario de búsqueda (ABB)**. El sistema permite registrar, atender y consultar mascotas, cumpliendo con el planteamiento académico solicitado.

---

## 🧩 Estructuras Implementadas

- **Lista Enlazada Simple**: usada para representar la **cola de espera** (orden FIFO).
- **Árbol Binario de Búsqueda (ABB)**: usado para **almacenar las mascotas registradas**, ordenadas por **ID único aleatorio**.

---

## 🧠 Funcionalidades

### 📌 Registro y atención de mascotas
- Al registrar una mascota:
  - Si ya existe (por ID), solo se agrega a la cola.
  - Si no existe, se genera un ID aleatorio único, se guarda en el árbol y en la cola.
- Las mascotas se **atienden por orden de llegada** (FIFO).

### 🌳 Árbol de mascotas
- Permite **buscar por ID o nombre**, **eliminar mascotas** y **mostrar el listado ordenado** (inorden).
- El recorrido del árbol y las eliminaciones son **recursivas**.

### 🖥️ Interfaz gráfica (Java Swing)
- Mostrar la cola actual y la mascota en atención.
- Permite registrar nuevas mascotas y atender a la siguiente.
- Mostrar el historial de mascotas en orden.

### 💾 Persistencia
- Al cerrar el sistema, la cola de espera se guarda en un archivo `colaEspera.txt`.
- Al iniciar, el sistema carga la cola desde el archivo, incluyendo las mascotas en el árbol.
- Se manejan errores de lectura/escritura con bloques `try-catch`.

---

## 🚀 Instrucciones

### 🛠️ Requisitos
- Java JDK 8 o superior**.
- Cualquier IDE (por ejemplo, IntelliJ, Eclipse, NetBeans) o terminal/síntesis de comandos.

### 🧪 Compilación y Ejecución

#### Uso del terminal (ejemplo de Windows)
1. Abra un prompt de comandos en la carpeta del proyecto.
2. Compile todos los archivos Java:
   ```bash
   javac *.java
   ```
3. Ejecute la aplicación:
   ```bash
   java VentanaVeterinaria
   ```

#### Usando un IDE
1. Abra la carpeta del proyecto en su IDE preferido.
2. Verifique que todos los archivos `.java` estén en el mismo paquete o directorio.
3. Localice y ejecute la clase `VentanaVeterinaria` (contiene el método `main`).

### 🖱️ Cómo usar la aplicación
- **Registrar una mascota:** Introduzca el nombre y tipo de la mascota, y haga clic en "Registrar".
- **Atender a la siguiente mascota:** Haga clic en "Atender" para atender a la siguiente mascota de la cola.
- **Eliminar una mascota:** Introduzca el ID de la mascota y haga clic en "Eliminar" para eliminarla del sistema.
- El panel izquierdo muestra la cola de espera actual; el panel derecho muestra todas las mascotas registradas en orden.
- Todos los datos se guardan automáticamente cuando se realizan cambios.
