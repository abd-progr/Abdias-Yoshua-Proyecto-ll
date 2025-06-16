public class Mascota{

    private int id;
    private String tipo;

    public Mascota(String tipo, int id){
        this.id = id;
        this.tipo = tipo;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    


}