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

## 🚀 Instrucciones de uso

### 🛠️ Requisitos
- **Java JDK 8 o superior**
- Editor o IDE: terminal

### 🧪 Compilación desde terminal (si no usás un IDE)
```bash
javac *.java
java VentanaVeterinaria
