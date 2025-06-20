// Nodo de lista enlazada, simple para la cola de espera
public class NodoLista {
    // Cada nodo guarda una mascota y referencia al siguiente
    private Mascota mascota;
    private NodoLista siguiente;

    // Constructor: recibe la mascota, al principio no tiene siguiente
    public NodoLista(Mascota m) {
        this.mascota = m;
        this.siguiente = null;
    }

    // Getter para la mascota
    public Mascota getMascota() {
        return mascota;
    }

    // Setter por si necesitas cambiar la mascota guardada
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    // Getter para el siguiente nodo
    public NodoLista getSiguiente() {
        return siguiente;
    }

    // Setter para enlazar este nodo con el siguiente
    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}