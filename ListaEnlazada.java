import java.io.PrintWriter;

// Lista enlazada simple para representar la cola de espera
public class ListaEnlazada {
    // Referencia al primer nodo de la lista
    private NodoLista cabeza;
    // Lleva la cuenta de cuántos elementos hay
    private int tamaño;

    // Constructor: comienza vacía
    public ListaEnlazada() {
        cabeza = null;
        tamaño = 0;
    }

    // Agrega una mascota al final de la lista (FIFO).
     
    public void agregar(Mascota m) {
        NodoLista nuevo = new NodoLista(m);
        if (cabeza == null) {
            // Lista vacía: el nuevo nodo es la cabeza
            cabeza = nuevo;
        } else {
            // Recorremos hasta el último nodo
            NodoLista actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            // Enlazamos el nuevo al final
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }

    // Saca y devuelve la mascota al frente de la lista.
    // Retorna null si la lista está vacía.

    public Mascota sacar() {
        if (cabeza == null) {
            return null;
        }
        Mascota m = cabeza.getMascota();
        // Avanzamos la cabeza al siguiente nodo
        cabeza = cabeza.getSiguiente();
        tamaño--;
        return m;
    }

    //Devuelve la mascota al frente sin sacarla.
    //Retorna null si la lista está vacía.
    public Mascota verFrente() {
        return (cabeza != null) ? cabeza.getMascota() : null;
    }

    //Indica si la lista está vacía.
    
    public boolean estaVacia() {
        return cabeza == null;
    }

    // Devuelve cuántos elementos hay en la lista.
    public int getTamaño() {
        return tamaño;
    }
    public NodoLista getCabeza() {
    return cabeza;
}
    // Guarda la lista en un archivo línea por línea
    public void guardarEnArchivo(PrintWriter pw) {
        NodoLista actual = cabeza;
        while (actual != null) {
            Mascota m = actual.getMascota();
            pw.println(m.getId() + "," + m.getTipo());
            actual = actual.getSiguiente();
        }
    }
    //Recorre la lista y construye una cadena con IDs y tipos de mascotas.
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