public class Mascota{

    private static final int RANGO_MAX = 10000; 
    private int id;
    private String tipo;

    public Mascota(String tipo){
        this.id = 0; 
        this.tipo = tipo;
        this.setId();
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setId() {
        long nuevoId;
        do {
            nuevoId = (long) (Math.random() * RANGO_MAX);
        } while (nuevoId == this.id);
    
        this.id = (int) nuevoId;
    }

    public int getId(){
        return this.id;
    }
    


}