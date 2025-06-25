// Nodo de lista enlazada, simple para la cola de espera
public class NodoLista {
    private Mascota mascota;
    private NodoLista siguiente;

    // Constructor: recibe la mascota, al principio no tiene siguiente
    public NodoLista(Mascota m) {
        this.mascota = m;
        this.siguiente = null;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}