
// Lista enlazada simple para representar la cola de espera
public class ListaEnlazada {
    // Referencia al primer nodo de la lista
    private NodoLista cabeza;
    private int tamaño;

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
            // Recorre hasta el último nodo
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

    public boolean estaVacia() {
        return cabeza == null;
    }

    public NodoLista getCabeza() {
        return cabeza;
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