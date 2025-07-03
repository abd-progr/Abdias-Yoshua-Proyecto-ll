/**
 * @brief Nodo para una lista enlazada que almacena un objeto `Mascota`.
 */
public class NodoLista {
    private Mascota mascota;
    private NodoLista siguiente;

    
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