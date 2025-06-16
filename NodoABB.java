public class NodoABB{

    private NodoABB derecha;
    private NodoABB izquierda;
    private Mascota mascota;

    public NodoABB(Mascota mascota){
        this.derecha = null;
        this.izquierda = null;
        this.mascota = mascota;

    }

    
    public void setDato(Mascota mascota){
        this.mascota = mascota;
    }
    public int getMascota(){
        return this.mascota;
    }
    public void setDerecha(NodoABB derecha){
        this.derecha = derecha;
    }
    public NodoABB getDerecha(){
        return this.derecha;
    }
    public void setIzquierda(NodoABB izquierda){
        this.izquierda = izquierda;
    }
    public NodoABB getIzquierda(){
        return this.izquierda;
    }



}