/**
 * @brief Implementación de una lista enlazada simple para almacenar mascotas.
*/
public class ListaEnlazada {
    
    private NodoLista cabeza;
    private int tamano;

    public ListaEnlazada() {
        cabeza = null;
        tamano = 0;
    }

/**
 * @brief Agrega una nueva mascota al final de la lista enlazada.
 * 
 * @param m La mascota a agregar (no puede ser null).
 * @post El tamaño de la lista aumenta en 1.
 * @post Si la lista estaba vacía, la nueva mascota se convierte en la cabeza.
 * @post Si no estaba vacía, se añade al final manteniendo los elementos existentes.
 */
     
    public void agregar(Mascota m) {
        NodoLista nuevo = new NodoLista(m);
        if (cabeza == null) {
            
            cabeza = nuevo;
        } else {
            
            NodoLista actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            
            actual.setSiguiente(nuevo);
        }
        tamano++;
    }
/**
 * @brief Elimina y devuelve la mascota al inicio de la lista (operación FIFO).
 * 
 * @return Mascota La mascota que estaba en la cabeza, o null si la lista estaba vacía.
 * @post Si había elementos, el tamaño disminuye en 1.
 * @post La nueva cabeza es el siguiente nodo (o null si era el único elemento).
 */
   
    public Mascota sacar() {
        if (cabeza == null) {
            return null;
        }
        Mascota m = cabeza.getMascota();
        
        cabeza = cabeza.getSiguiente();
        tamano--;
        return m;
    }

/**
 * @brief Verifica si la lista no contiene elementos.
 * 
 * @return true si la lista está vacía (cabeza == null), false en caso contrario.
 */
    public boolean estaVacia() {
        return cabeza == null;
    }

    public NodoLista getCabeza() {
        return cabeza;
    }
/**
 * @brief Elimina la primera mascota con el ID especificado de la lista enlazada.
 * 
 * @param id ID de la mascota a eliminar.
 * @return boolean true si se encontró y eliminó la mascota, false si no existía.
 * 
 * @post Si la mascota estaba en la cabeza, actualiza la referencia cabeza.
 * @post Si la mascota estaba en medio, re-enlaza los nodos adyacentes.
 * @post El tamaño de la lista disminuye en 1 si se eliminó un elemento.
 */
    public boolean eliminarPorId(int id) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.getMascota().getId() == id) {
            cabeza = cabeza.getSiguiente();
            tamano--;
            return true;
        }

        NodoLista actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getMascota().getId() == id) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamano--;
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }
/**
 * @brief Genera una representación en String de toda la lista enlazada.
 * 
 * @return String Cadena con el formato: "[ID=x, Tipo=y] -> [ID=z, Tipo=w] -> null"
 */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodoLista actual = cabeza;
        while (actual != null) {
            Mascota m = actual.getMascota();
            sb.append("[ID=").append(m.getId())
              .append(", Tipo=").append(m.getTipo())
              .append("] → ");
            actual = actual.getSiguiente();
        }
        sb.append("null");
        return sb.toString();
    }

}