import java.io.*;
/**
 * @brief Clase principal que gestiona una veterinaria con sistema de cola y registro de mascotas.
 * 
 */
public class Veterinaria {
    private ListaEnlazada cola;
    private ArbolMascotas arbol;
    private static final String ARCHIVO_COLA = "cola.txt";

    public Veterinaria(ArbolMascotas a) {
        this.arbol = a;
        this.cola = new ListaEnlazada();
        cargarCola();
    }
/**
 * @brief Registra una nueva mascota en el sistema y la añade a la cola de atención.
 * 
 * @param nombre Nombre de la mascota (no puede ser null o vacío).
 * @param tipo Tipo de mascota.
 * @post Si la mascota no existía, se crea y registra en el árbol.
 * @post La mascota se añade al final de la cola de atención.
 * @post Actualiza automáticamente el archivo.
 */
    public void registrar(String nombre, String tipo) {
        Mascota m = arbol.buscarPorNombre(nombre);
        if (m == null) {
            m = new Mascota(nombre, tipo);
            arbol.agregarMascota(m);
        }
        cola.agregar(m);
        guardarCola();
    }

/**
 * @brief Atiende a la siguiente mascota en la cola (FIFO).
 * 
 * @return Mascota La mascota atendida, o null si la cola está vacía.
 * @post Elimina la mascota de la cola de atención.
 * @post Actualiza automáticamente el archivo.
 */   
    public Mascota atender() {
        Mascota m = cola.sacar();
        guardarCola();
        return m;
    }
/**
 * @brief  una representación visual de la cola actual.
 */
    public String verCola() {
        return cola.toString();
    }
/** 
 * @brief Elimina una mascota tanto del árbol como de la cola de atención usando su ID.
 * 
 * @param id ID de la mascota a eliminar.
 * @return boolean true si se eliminó correctamente, false si no se encontró la mascota.
 * 
 * @post Si la mascota existía:
 *   - Se elimina del árbol binario
 *   - Se remueve de la cola de atención (si estaba presente)
 *   - Se actualiza el archivo
 */  
    public boolean eliminarMascotaPorId(int id) {
        boolean eliminadoDelArbol = arbol.eliminar(id);
        if (!eliminadoDelArbol) {
            return false; 
        }
       
        cola.eliminarPorId(id);
        guardarCola();
        return true;
    }

  /**
 * @brief Guarda el estado actual de la cola en el archivo.
 * @note Maneja automáticamente errores de IO mostrándolos por consola.
 */   
    private void guardarCola() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_COLA))) {
            NodoLista actual = cola.getCabeza();
            while (actual != null) {
                Mascota m = actual.getMascota();
                pw.println(m.getNombre() + "," + m.getTipo() + "," + m.getId());
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            System.err.println("Error guardando cola: " + e.getMessage());
        }
    }

/**
 * @brief Carga la cola de atención desde el archivo de persistencia.
 * @post Si el archivo existe y es válido:
 *   - Crea las mascotas y las registra en el árbol
 *   - Reconstruye la cola de atención en orden FIFO
 */
    private void cargarCola() {
        File f = new File(ARCHIVO_COLA);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    String tipo = partes[1];
                    int id = Integer.parseInt(partes[2]);

                    Mascota m = new Mascota(nombre, tipo, id);
                    arbol.agregarMascota(m);
                    cola.agregar(m);
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando cola: " + e.getMessage());
        }
    }
/**
 * @brief Genera un listado ordenado por ID de todas las mascotas registradas.
 */
    public String verMascotasOrdenadas() {
        return arbol.recorridoEnOrden();
    }
/**
 * @brief Elimina una mascota del sistema mediante su ID.
* @return boolean true si la mascota existía y fue eliminada, false si no se encontró.
 */
    public boolean eliminarPorId(int id) {
        return arbol.eliminar(id);
    }
    
/**
 * @brief Busca una mascota por su nombre (comparación insensible a mayúsculas).
 * @return Mascota La mascota encontrada o null si no existe.
 */ 
    public Mascota buscarPorNombre(String nombre) {
        return arbol.buscarPorNombre(nombre);
    }
}
