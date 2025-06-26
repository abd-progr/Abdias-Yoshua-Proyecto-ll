public class ArbolMascotas {
    private NodoABB raiz;

    public void agregarMascota(Mascota m) {
        raiz = agregarRecursivo(raiz, m);
            }
        

    // Método auxiliar para agregar una mascota al árbol
    private NodoABB agregarRecursivo(NodoABB actual, Mascota m) {
        if (actual == null) return new NodoABB(m);
        if (m.getId() < actual.getMascota().getId())
            actual.setIzquierda(agregarRecursivo(actual.getIzquierda(), m));
        else if (m.getId() > actual.getMascota().getId())
            actual.setDerecha(agregarRecursivo(actual.getDerecha(), m));
        return actual;
    }

    public Mascota buscarPorId(int id) {
        NodoABB nodo = buscarRecursivo(raiz, id);
        return nodo == null ? null : nodo.getMascota();
    }

    //  buscar un nodo por id
    private NodoABB buscarRecursivo(NodoABB nodo, int id) {
        if (nodo == null) return null;
        if (id == nodo.getMascota().getId()) return nodo;
        if (id < nodo.getMascota().getId())
            return buscarRecursivo(nodo.getIzquierda(), id);
        else
            return buscarRecursivo(nodo.getDerecha(), id);
    }

    public Mascota buscarPorNombre(String nombre) {
        return buscarNombreRecursivo(raiz, nombre);
    }

    //  buscar una mascota por nombre
    private Mascota buscarNombreRecursivo(NodoABB nodo, String nombre) {
        if (nodo == null) return null;
        if (nodo.getMascota().getNombre().equalsIgnoreCase(nombre))
            return nodo.getMascota();
        Mascota izq = buscarNombreRecursivo(nodo.getIzquierda(), nombre);
        if (izq != null) return izq;
        return buscarNombreRecursivo(nodo.getDerecha(), nombre);
    }

    public boolean eliminar(int id) {
        if (buscarPorId(id) == null) return false;
        raiz = eliminarRecursivo(raiz, id);
        return true;
    }

    private NodoABB eliminarRecursivo(NodoABB nodo, int id) {
        if (nodo == null) return null;

        if (id < nodo.getMascota().getId())
            nodo.setIzquierda(eliminarRecursivo(nodo.getIzquierda(), id));
        else if (id > nodo.getMascota().getId())
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), id));
        else {
            if (nodo.getIzquierda() == null) return nodo.getDerecha();
            if (nodo.getDerecha() == null) return nodo.getIzquierda();

            Mascota menor = encontrarMinimo(nodo.getDerecha());
            nodo.setMascota(menor);
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), menor.getId()));
        }
        return nodo;
    }
    //  encontrar el nodo con el valor mínimo 
    private Mascota encontrarMinimo(NodoABB nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo.getMascota();
    }
    

    public String recorridoEnOrden() {
        StringBuilder sb = new StringBuilder();
        enOrdenRecursivo(raiz, sb);
        return sb.toString();
    }

    // ecorrer el árbol en orden
    private void enOrdenRecursivo(NodoABB nodo, StringBuilder sb) {
        if (nodo != null) {
            enOrdenRecursivo(nodo.getIzquierda(), sb);
            sb.append(nodo.getMascota()).append("\n");
            enOrdenRecursivo(nodo.getDerecha(), sb);
        }
    }
}
