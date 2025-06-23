public class ArbolMascotas{

    private NodoABB raiz;

    public ArbolMascotas(Mascota mascota){
        this.raiz = new NodoABB( mascota);
    }
    
    public void agregarMascota(Mascota mascota){
        
        raiz = agregarRecursivo(this.raiz, mascota);
    }

    private NodoABB agregarRecursivo(NodoABB actual, Mascota mascota){

        if(actual == null){
            return new NodoABB(mascota);
        }
        if(mascota.getId() < actual.getMascota().getId()){
            actual.setIzquierda(agregarRecursivo(actual.getIzquierda(), mascota));
        } else if(mascota.getId() > actual.getMascota().getId()){
            actual.setDerecha(agregarRecursivo(actual.getDerecha(), mascota));
        } else {
            // Si el ID ya existe, no se agrega
            return actual;
        }
        return actual;

    }

    public boolean eliminarMascota(int identificador){

        NodoABB nodoEliminar = buscarRecursivo(this.raiz, identificador);
        if (nodoEliminar == null) return false;
        
        raiz = eliminarRecursivo(identificador, this.raiz);
        return true;

    }
    private NodoABB eliminarRecursivo(int identificador, NodoABB nodo){

        if(nodo == null){
            return null;
        }
        if(nodo.getMascota().getId() > identificador) {
            nodo.setIzquierda(eliminarRecursivo(identificador, nodo.getIzquierda()));
        }
        if(nodo.getMascota().getId() < identificador) {
            nodo.setDerecha(eliminarRecursivo(identificador, nodo.getDerecha()));
        }

        if(nodo.getMascota().getId() == identificador){
            //caso sin hijos
            if(nodo.getDerecha() == null && nodo.getIzquierda() == null){
                return null;

            }
            // caso un hijo
            if(nodo.getDerecha() == null){
                return nodo.getIzquierda();
            }
            if(nodo.getIzquierda() == null){
                return nodo.getDerecha();
            }

            //caso 2hijos
            Mascota mascotaMin = encontrarMinimo(nodo.getDerecha());
            nodo.setMascota(mascotaMin);
            nodo.setDerecha(eliminarRecursivo(mascotaMin.getId(), nodo.getDerecha()));
            return nodo;
        }
        return nodo;
    }
    private Mascota encontrarMinimo(NodoABB nodo){

        if (nodo.getIzquierda() == null) {
            return nodo.getMascota();
        } else {        
            return encontrarMinimo(nodo.getIzquierda());
        }
    }

    public Mascota buscarMascota(int identificador){

        raiz = buscarRecursivo(this.raiz, identificador);
        if(raiz == null) {
            return null;
        } else{
        return raiz.getMascota();
        }

    }
    private NodoABB buscarRecursivo(NodoABB nodo, int identificador){

        if(nodo == null) return null;

        if(nodo.getMascota().getId() == identificador) return nodo;

        if(nodo.getMascota().getId() > identificador){
            nodo.setIzquierda(buscarRecursivo(nodo.getIzquierda(), identificador));
        } else{
            nodo.setDerecha(buscarRecursivo(nodo.getDerecha(), identificador));
        }
        return nodo;

    }
    public void recorridoEnOrden(){

        recorridoEnOrdenRecursivo(raiz);

    }
    

    public String listadoMascotasOrdenado() {
    StringBuilder sb = new StringBuilder();
    listadoRecursivo(raiz, sb);
    return sb.toString();
}

    private void listadoRecursivo(NodoABB nodo, StringBuilder sb) {
        if (nodo != null) {
            listadoRecursivo(nodo.getIzquierda(), sb);
            sb.append("ID: ").append(nodo.getMascota().getId())
            .append(", Tipo: ").append(nodo.getMascota().getTipo()).append("\n");
            listadoRecursivo(nodo.getDerecha(), sb);
        }
    }


    public void recorridoEnOrdenRecursivo(NodoABB nodo) {

        if (nodo != null && nodo.getMascota() != null) {
            NodoABB nodoIzquierdo = nodo.getIzquierda();
            NodoABB nodoDerecho = nodo.getDerecha();
        
            recorridoEnOrdenRecursivo(nodoIzquierdo);
            System.out.println("ID: " + nodo.getMascota().getId() + 
                            ", Tipo: " + nodo.getMascota().getTipo());
            recorridoEnOrdenRecursivo(nodoDerecho);
        }
    }

}